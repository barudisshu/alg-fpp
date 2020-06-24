How to install Copris

1. Check the version of your Scala

```shell script
$ scala -version
Scala code runner version 2.12.5 -- ...
```

Copris can run on Scala version 2.12 (not on ther versions).

2. Download Copris package

```shell script
$ wget http://bach.istc.kobe-u.ac.jp/copris/packages/copris-v2-3-1.zip
```

3. Unzip Copris package

```shell script
$ unzip copris-v2-3-1.zip
```

4. Move to Copris examples directory

```shell script
cd copris-v2-3-1/examples
```

5. Compile Examples.scala

```shell script
# Mac OS X, Linux
$ scalac -cp ../build/copris-all-v2-3-1.jar Examples.scala

# Windows
> scalac -cp ..\build\copris-all-v2-3-1.jar Examples.scala
```

Alternatively, you can use coprisc command.

```shell script
# Mac OS X, Linux
$ ./coprisc Examples.scala
```

6. Run the program

```shell script
# Mac OS X, Linux
$ scala -cp ../build/copris-all-v2-3-1.jar FirstStep
Solution(Map(x -> 4, y -> 3),Map())

# Windows
> scala -cp ..\build\copris-all-v2-3-1.jar FirstStep
Solution(Map(x -> 4, y -> 3),Map())
```

Alternatively, you can use copris command.

```shell script
# Mac OS X, Linux
$ ./copris FirstStep
```


