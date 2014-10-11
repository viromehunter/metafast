package tools;

import algo.ComponentsBuilder;
import ru.ifmo.genetics.utils.tool.values.InValue;
import structures.ConnectedComponent;
import io.IOUtils;
import ru.ifmo.genetics.structures.map.ArrayLong2IntHashMap;
import ru.ifmo.genetics.utils.tool.ExecutionFailedException;
import ru.ifmo.genetics.utils.tool.Parameter;
import ru.ifmo.genetics.utils.tool.Tool;
import ru.ifmo.genetics.utils.tool.inputParameterBuilder.*;

import java.io.*;
import java.util.*;

public class ComponentCutterMain extends Tool {
    public static final String NAME = "component-cutter";
    public static final String DESCRIPTION = "Build graph components from tangled graph";

    public final Parameter<Integer> k = addParameter(new IntParameterBuilder("k")
            .mandatory()
            .withShortOpt("k")
            .withDescription("k-mer size")
            .create());

    public final Parameter<Integer> minLen = addParameter(new IntParameterBuilder("min-seq-len")
            .withShortOpt("l")
            .withDescription("minimum sequence length to be added")
            .withDefaultValue(100)
            .create());

    public final Parameter<Integer> minComponentSize = addParameter(new IntParameterBuilder("min-component-size")
            .withShortOpt("b1")
            .withDescription("minimum component size in component-cutter (in k-mers)")
            .withDefaultValue(1000)
            .create());

    public final Parameter<Integer> maxComponentSize = addParameter(new IntParameterBuilder("max-component-size")
            .withShortOpt("b2")
            .withDescription("maximum component size in component-cutter (in k-mers)")
            .withDefaultValue(10000)
            .create());

    public final Parameter<File[]> sequencesFiles = addParameter(new FileMVParameterBuilder("sequences")
            .withShortOpt("i")
            .mandatory()
            .withDescription("list of input files")
            .create());

    public final Parameter<File> componentsFile = addParameter(new FileParameterBuilder("components-file")
            .withDescription("file to write found components to")
            .withDefaultValue(workDir.append("components.bin"))
            .create());


    // output values
    public final InValue<File> componentsFileOut = addOutput("components-file", componentsFile, File.class);


    @Override
    protected void runImpl() throws ExecutionFailedException {
        ArrayLong2IntHashMap hm =
                new ArrayLong2IntHashMap((int) (Math.log(availableProcessors.get()) / Math.log(2)) + 4);
        try {
            IOUtils.addFASTASequences(sequencesFiles.get(), hm, k.get(), minLen.get(), this.logger);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<ConnectedComponent> components;
        try {
            String statFP = workDir + File.separator + "components-stat-" +
                    minComponentSize.get() + "-" + maxComponentSize.get();
            components = ComponentsBuilder.splitStrategy(hm, k.get(), minComponentSize.get(),
                    maxComponentSize.get(), statFP, this.logger, availableProcessors.get());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        info(components.size() + " components found");

        try {
            ConnectedComponent.saveComponents(components, componentsFile.get().getAbsolutePath());
            info("Components printed to " + componentsFile.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void cleanImpl() {
    }

    public static void main(String[] args) {
        new ComponentCutterMain().mainImpl(args);
    }

    public ComponentCutterMain() {
        super(NAME, DESCRIPTION);
    }
}
