# About this project
The presented project is academic project.
Original repository is located at https://github.com/visualvm/visualvm.src. Original documentation is located at https://visualvm.github.io

# VisualVM sources repository

VisualVM is a visual tool integrating commandline JDK tools and lightweight profiling capabilities. See https://visualvm.github.io for details and downloads.

## Get the tools

Use Apache Ant and Oracle JDK 7 to build VisualVM from this repository.

## Get the sources

First download or clone this repository into directory `visualvm.src`. There are two project suites included:
  * visualvm (`visualvm.src/visualvm`) - suite for the core VisualVM tool
  * plugins (`visualvm.src/plugins`) - suite for the VisualVM plugins available in Plugins Center

## Get the dependencies
  
Then download the [NetBeans 8.0.2 platform and profiler binaries](https://github.com/visualvm/visualvm.src/raw/master/visualvm/nb802_visualvm_02102016.zip) and extract them into directory `visualvm.src/visualvm` (should create `visualvm.src/visualvm/netbeans`).

## Build and run VisualVM tool

To build VisualVM, use `ant build-zip` command in the `visualvm.src/visualvm` directory. To run VisualVM, use `ant run` command in the `visualvm.src/visualvm` directory.
