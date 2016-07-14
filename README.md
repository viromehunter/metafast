# MetaFast

<img align="right" src="logo.jpg" alt="MetaFast" width="500">

**MetaFast** (METAgenome FAST analysis toolkit) is a toolkit for calculating a number of statistics of 
metagenome sequences and building the distance matrix between them.

Authors:
* **Software:** *Sergey Kazakov* and *Vladimir Ulyantsev*, <br/>
ITMO University, Saint-Petersburg, Russia.
* **Testing:** *Veronika Dubinkina* and *Alexandr Tyakht*, <br/>
SRI of Physical-Chemical Medicine, Moscow, Russia.
* **Idea, supervisor:** *Dmitry Alexeev*, <br/>
SRI of Physical-Chemical Medicine, Moscow, Russia.


**MetaFast** documentation is available on the <a href="https://github.com/ctlab/metafast/wiki" target="_blank">MetaFast GitHub wiki</a>.<br/>
Here is a short version of it.



## Content

* [Installation](https://github.com/ctlab/metafast#installation)
* [Running instructions](https://github.com/ctlab/metafast#running-instructions)
* [Example](https://github.com/ctlab/metafast#example)
* [Citation](https://github.com/ctlab/metafast#citation)
* [Contact](https://github.com/ctlab/metafast#contact)
* [License](https://github.com/ctlab/metafast#license)
* [See also](https://github.com/ctlab/metafast#see-also)



## Installation

To run MetaFast you need to have JRE 1.6 or higher installed and only one script (`metafast.sh`, `metafast.bat` or `metafast.jar`).

You can download it from the last stable release in the GitHub <a href="https://github.com/ctlab/metafast/releases">'Releases' section</a>.

* For *Linux* and *Mac OS*: download `metafast.sh`, run the command `chmod a+x metafast.sh`, then run `./metafast.sh` from the command line.
* For *Windows*: download `metafast.bat` and run it from the command line.
* For other OS: download `metafast.jar` and run it via command `java -jar metafast.jar`.


Alternatively, you can build the newest version of the MetaFast from the repository:
~~~
git clone https://github.com/ctlab/metafast.git
cd metafast
ant
./out/metafast.sh --version
~~~


## Running instructions

To run MetaFast use the following syntax:
* `metafast.sh [<Launch options>] [<Input parameters>]`
* `metafast.bat [<Launch options>] [<Input parameters>]`
* `java -jar metafast.jar [<Launch options>] [<Input parameters>]`

To view help for launch options and input parameters run `metafast.sh --help` or `metafast.sh --help-all`.

By running MetaFast a working directory is created (by default `./workDir/`). 
All intermidiate files, log file and final results are saved in it. 

File `output_description.txt` is created after every run in the current and working directories. 
It contains the description of every output file produced by the MetaFast.

Metafast run script also allows you to run subtools of whole process or different tools, that was included into the package. 
To see the list of available additional tools, run `metafast.sh --tools`.


## Example

Download [tinytest_A.fastq](https://github.com/ctlab/metafast/raw/master/test_data/tinytest_A.fastq) and 
[tinytest_B.fastq](https://github.com/ctlab/metafast/raw/master/test_data/tinytest_B.fastq) and run the command:
~~~
./metafast.sh -k 7 -b 0 -l 8 -b1 3 -i tinytest_A.fastq tinytest_B.fastq
~~~

After it has finished, a distance matrix can be found in `workDir/matrices/dist_matrix_<date>_<time>.txt`:
~~~
#	tinytest_A	tinytest_B
tinytest_A	0.0	0.09090909090909091
tinytest_B	0.09090909090909091	0.0
~~~

The element `matrix[i][j]` is a distance between *sample i* and *sample j*.

K-mers frequency statistics is saved in `workDir/kmer-counter-many/stats/<in-file>.stat.txt`;<br/>
image file with heatmap and dendrogram is saved in `workDir/matrices/dist_matrix_<date>_<time>_heatmap.png`:<br/>
<img src="test_data/test_heatmap.png" alt="Test heatmap" width="450">


## Citation

If you use MetaFast in your research, please cite the following publication:

Ulyantsev V.I., Kazakov S.V., Dubinkina V.B., Tyakht A.V. & Alexeev D.G. (2016). 
MetaFast: fast reference-free graph-based comparison of shotgun metagenomic data. 
Bioinformatics, btw312. 
[doi: 10.1093/bioinformatics/btw312](http://bioinformatics.oxfordjournals.org/content/early/2016/06/16/bioinformatics.btw312)


## Contact

Please report any problems directly to the github [issue tracker](https://github.com/ctlab/metafast/issues).<br/>
Also, you can send your feedback to [svkazakov@rain.ifmo.ru](mailto:svkazakov@rain.ifmo.ru).


## License

The MIT License (MIT)


## See also

* [khmer](https://github.com/ged-lab/khmer) - a toolkit to split reads.
* [crAss](http://edwards.sdsu.edu/crass/) - Cross-Assembly of Metagenomes.
* [MaryGold](http://sourceforge.net/projects/metavar/) - Variation analysis of metagenomic samples.

