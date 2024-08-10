![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/PolyRocketMatt/vectorize/deployment.yml?color=68AD63&style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-%2368AD63?style=for-the-badge)
![Kotlin](https://img.shields.io/badge/Java-21-%233e7fa8?logo=java&style=for-the-badge)

<p align="center">
    <picture>
        <source srcset="img/cyclone-logo-512.png" media="(prefers-color-scheme: dark)">
        <source srcset="img/cyclone-logo-512.png" media="(prefers-color-scheme: light)">
        <img width="128" height="128" src="img/cyclone-logo-512.png" />
    </picture>
</p>

<h1 align="center">Cyclone</h1>

**Cyclone** is a library that aims at using hardware acceleration to perform operations on multidimensional
arrays. Through the use of [TornadoVM](https://www.tornadovm.org/), Cyclone is able to offload operations
onto hardware specialized in parallel computation (e.g. GPUs). It is designed to be an easy-to-use, all-in-one
library for operations that are embarrassingly parallel.

## Installation

Currently, Cyclone is still very much a WIP. The following steps can be followed to build and run the
library:

```shell
git clone https://github.com/PolyRocketMatt/Cyclone.git
cd Cyclone
./gradlew build
```

Please note that since this library makes use of TornadoVM, it is necessary to have its SDK installed.
A guide on how to do this can be found [here](https://tornadovm.readthedocs.io/en/latest/installation.html).

## Features

Cyclone is still in early development. The following features are planned for the library:

- [ ] Arithmetic operations on n-d arrays
- [ ] Utility functions for n-d arrays
- [ ] ... more to be announced later

---
Icons made by [Freepik](https://www.freepik.com) from [Flaticon](https://www.flaticon.com/)

---