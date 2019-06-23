# BOOM - Berkeley Out-of-Order Machine Fork
This repository contains an example of [BOOM](https://github.com/riscv-boom/riscv-boom) and [RocketChip](https://github.com/chipsalliance/rocket-chip) integration for non-Berkeley projects.

Berkeley students are not much concerned about outside production flows. Their integration flow is a headache for all external contributors.

In this project, we update the default Berkeley flow and fork both RocketChip and BOOM repositories. Source files are 
simply periodically copied from their repos. However, scripts and build files are maintained separately.

## Versions
You may find both RC and BOOM versions in their RCVERSION and BOOMVERSION files

## Usage

### Compile
```bash
> sbt
> compile
```

### Run Scalafmt format over the whole code base:
```bash
> sbt
> fmt 
```
Enjoy your hacking !
