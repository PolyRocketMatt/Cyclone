![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/PolyRocketMatt/vectorize/deployment.yml?color=68AD63&style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-%2368AD63?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-21-%233e7fa8?logo=java&style=for-the-badge)

<p align="center">
    <picture>
        <source srcset="img/cyclone.png" media="(prefers-color-scheme: dark)">
        <source srcset="img/cyclone.png" media="(prefers-color-scheme: light)">
        <img width="128" height="128" src="img/cyclone.png" />
    </picture>
</p>

<h1 align="center">Cyclone</h1>

**Cyclone** is a library that aims at using hardware acceleration to perform operations on tensor.  Through the use of 
[TornadoVM](https://www.tornadovm.org/), Cyclone is able to offload operations onto heterogeneous hardware specialized in parallel computation 
(e.g. GPU or FPGA). The library offers a wide variety of wrappers implementing computation kernels for common operations 
on n-dimensional arrays. This allows developers to write high-performance code in Java, while still benefiting from the 
expansive Java ecosystem and the parallel computation capabilities of TornadoVM.

## Installation

Cyclone requires [TornadoVM](https://github.com/beehive-lab/TornadoVM) to run. Make sure it is installed before
installing Cyclone. You can check if TornadoVM is correctly installed by running the following command:

```shell
tornado --version
```

To install Cyclone as a local maven repository, follow the steps below:

```shell
git clone https://github.com/PolyRocketMatt/Cyclone.git
cd Cyclone
./gradlew clean build publishToMavenLocal
```

## Features

Cyclone is still in early development. The following features are planned for the library:

- [ ] WIP

---

Icon provided by [IconBaandar](https://www.flaticon.com/free-icon/cyclone_9442994?page=7&position=1&term=cyclone&origin=style-search&related_id=9442994) from [Flaticon](https://www.flaticon.com/)

---