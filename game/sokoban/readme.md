推箱子
=====

下面是输入文件：

```text
7
6
######
# .###
#  ###
#*@  #
#  $ #
#  ###
######
```

- 第一行给出的数为行数
- 第二行给出的数为列数
- 查看更多有关 [推箱子](http://www.sokobano.de/wiki/index.php?title=Level_format) 的定义


使用方法：

```shell script
$ scala -cp copris-sokoban-v1-0.jar sokoban.Solver -v -o opt -s2 minisat data/m1-00001.txt
File = data/m1-00001.txt
Solver = minisat
Options = opt
Rows = 7
Cols = 6
Steps = 8
Step = 0
######
# .###
#  ###
#*@  #
#  $ #
#  ###
######
Step = 1
######
# .###
#$ ###
#+   #
#  $ #
#  ###
######
Step = 2
######
# .###
#$ ###
#.   #
# $@ #
#  ###
######
Step = 3
######
# .###
#$ ###
#.$  #
# @  #
#  ###
######
Step = 4
######
# .###
#$ ###
#.@$ #
#    #
#  ###
######
Step = 5
######
# .###
#@ ###
#* $ #
#    #
#  ###
######
Step = 6
######
# .###
#  ###
#*$@ #
#    #
#  ###
######
Step = 7
######
# .###
# $###
#*@  #
#    #
#  ###
######
Step = 8
######
# *###
# @###
#*   #
#    #
#  ###
######
```

性能评估：

Solver performance is measured on version 1.0.

- Copris solver was run on Intel Xeon 3.33GHz machine with:
    - Ubuntu Linux 12.04 (64 bit)
    - Java version "1.6.0_27", OpenJDK Runtime Environment (with "-Xmx4G" option)
    - Scala version 2.9.1
    - Copris version 2.0.0
    - Sugar version 2.0.0
- Glucose version 2.1 (with preprocessing) is used as a SAT solver.