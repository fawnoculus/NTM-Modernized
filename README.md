![NTM-Modernized Icon](https://raw.githubusercontent.com/fawnoculus/NTM-Modernized/refs/heads/master/.idea/icon.png)
# NTM-Modernized
[![Available on Fabric](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.3.1/assets/cozy/supported/fabric_64h.png)](https://fabricmc.net/)
[![Available on Neoforge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.3.1/assets/cozy/supported/neoforge_64h.png)](https://neoforged.net/)
[![Available on Quilt](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.3.1/assets/cozy/supported/quilt_64h.png)](https://quiltmc.net/)

[![Licensed under: GPL-3.0](https://img.shields.io/github/license/fawnoculus/NTM-Modernized?style=flat&color=900c3f)](https://github.com/fawnoculus/NTM-Modernized/blob/master/LICENSE.txt)
[![Build Workflow](https://github.com/fawnoculus/NTM-Modernized/actions/workflows/build.yml/badge.svg)](https://github.com/fawnoculus/NTM-Modernized/actions/workflows/build.yml)

## What is NTM-Modernized?
NTM-Modernized is an Attempt to port [HBM's NTM](https://github.com/HbmMods/Hbm-s-Nuclear-Tech-GIT) (a mod for 1.7.10) to modern Versions of Minecraft.

## DISCLAIMERS
1. I am not in any way associated with HBM (aka Bob), I am working on this "Port" purely because I liked HBM's NTM and at some point thought "Wow, this would be really cool in newer Versions!"
2. Don't bother contributors of the original mod with problems of this one! (We don't want to bother them with hundreds of unnecessary Bug Reports!)
3. This is not supposed to be a 1:1 exact port. Certain features will be changed for various reasons, see [Changes.md](./docs/Changes.md) for details on what changed and why.
4. NTM-Modernized is not even remotely finished!
   Most Features are still missing, unfinished, unpolished or barely functioning.
   There will be no release of mod on Modrinth / CurseForge until It is at least in a *"playable"* State

## Installing NTM-Modernized
In the future there may also be Releases on Modrinth & CurseForge.
For now your Options for getting this mod are:
* ~~Modrinth~~ (maybe someday)
* ~~CurseForge~~ (maybe someday)
* [GitHub Releases](https://github.com/fawnoculus/NTM-Modernized/releases/latest)
* [GitHub Actions](https://github.com/fawnoculus/NTM-Modernized/actions/workflows/build.yml) (refer to the Section Below)
* Building it from Source (refer to the Section Below)

## Downloading from GitHub actions
1. Navigate to the Latest (topmost) successfully ran (green check) [Action](https://github.com/fawnoculus/NTM-Modernized/actions/workflows/build.yml)
2. Click on that bad boy
3. On the bottom right there should be a button for downloading the Artifact (you may need to scroll down)
4. If you unzip the File you should now have a working versions of the mod for all modloaders (you don't want the files that end in "-sources.jar")

## Building it from Source
Building it from source should be unnecessary as you can download a jar of the latest commit from [GitHub Actions](https://github.com/fawnoculus/NTM-Modernized/actions/workflows/build.yml)
* Make sure you have [**JDK-21**](https://adoptium.net/temurin/releases/?variant=openjdk8&jvmVariant=hotspot&package=jdk&version=21) and [**git**](https://git-scm.com/downloads) installed
* Open PowerShell (or Bash if you are using Linux)
* Navigate to the Directory you wish to copy the Sources to
```shell
cd $HOME/Downloads/
```
* Download the Sources
```shell
git clone https://github.com/fawnoculus/NTM-Modernized
```
* enter the sources directory
```shell
cd NTM-Modernized
```
* build the Mod
```shell
./gradlew build
```
If the Command Returns with saying **BUILD SUCCESSFUL** then you should be able to find the mod file at
**"Downloads/NTM-Modernized/***MODLOADER***/build/libs/NTM-Modernized-***MC_VERSION***-***MODLOADER***-***VERSION***.jar"**,
where ***MODLOADER*** is replaced by the Modloader for which you want the mod jar

## Contributing
Please see [CONTRIBUTING.md](./docs/CONTRIBUTING.md) for information of how you can aid in development of this port.

## Licence
This software is licensed under the GNU Public License version 3.0 In short: This software is free, you may run the software freely, create modified versions,
distribute this software and distribute modified versions, as long as the modified software too has a free software license. The full license can be found in the `LICENSE.txt` file.
