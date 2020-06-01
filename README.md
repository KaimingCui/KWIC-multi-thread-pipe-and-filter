# KWIC-multi-thread-pipe-and-filter

## Objective

This project implements the KWIC system (see description below) using pipe-andfilter architectural pattern and multi thread. 

## KWIC System

The KWIC system accepts an ordered set of lines, each line is an ordered set of words,
and each word is an ordered set of characters. Any line may be “circularly shifted” by
repeatedly removing the first word and appending it at the end of the line. The KWIC
index system outputs a listing of all circular shifts of all lines in alphabetical order.

For example:

![GitHub](https://github.com/KaimingCui/KWIC-multi-thread-pipe-and-filter/blob/master/1.png)

### Required to perform following tasks.
1. Implement KWIC system using pipe-and-filter architectural pattern. Following
are the key components of this system.

• Input filter, which reads the content of a KWIC input file, parses it, and
writes the parsed lines to its output stream.

• CircularShifter filter connected with a pipe to the output stream of Input
filter. Thus, the lines produced by Input filter serve as input for
CircularShifter filter. CircularShifter processes the input lines producing
circular shifts of those lines. The produced shifts are written to the
CircularShifter's output stream.

• Alphabetizer filter connected with a pipe to the output stream of
CircularShifter filter. Thus, circular shifts produced by CircularShifter
serve as input for Alphabetizer. Alphabetizer sorts these shifts
alphabetically and writes them to its output stream.

• Output filter connected with a pipe to the output stream of Alphabetizer.
Thus, Output reads sorted shifts from its input stream and writes them to
the standard output.

• Control, which manages filter and pipe mechanism, by creating a pipeline
of the above described filters.

2. To support enhancements, you are you are required to implement another filter
called “StopwordsRemover”. This filter removes shifts starting with certain words
from the data flow in the KWIC system. The starting words that decide which
shifts should be removed from the data flow are denoted as stopwords such as a,
an, the, etc. Stopwords should be defined in another file, which must be passed to
the KWIC system as the second command line argument (the first command line
argument is the name of the input KWIC file). The format of the stopwords file is
very simple: A single stopwords is stored on a single line in the file. Thus, the
system should parse the stopwords file, keep all stopwords in the memory,
compare the first word of each single shift with all of stopwords, and finally
remove all shifts that start with a stopword.

3. To further extend the system’s capabilities, implement another filter that
transforms the content of a single line in the following way. The first word of the
line is capitalized i.e., all characters of the first word are converted to upper case.

For Example:

Pipe Filter 

Transformed as

PIPE Filter

You need to apply this mechanism for both all original lines and all circular shifts.
Thus, first all original lines are transformed resulting in each original line starting
with a capitalized word. Secondly, we make circular shifts with transformed lines.
Finally, all circular shifts are transformed resulting in each circular shift starting
with a capitalized word.

For example：

Pipe and Filter Architecture

Transformed as

PIPE and Filter Architecture

ARCHITECTURE PIPE and Filter 

FILTER Architecture PIPE and

AND Filter Architecture PIPE

## Explain my program

Pipe class represents a pipe connect two components (Filters). It has PipeReader and PipeWriter that allow characters can go through the pipe from one filter to another filter.

Filter abstract class represents a component in KWIC system. It contains two Pipe object. One for read another for write. Filter implements Runable interface so that each filter can be a thread.

The other functional Filters that extends Filter class.


