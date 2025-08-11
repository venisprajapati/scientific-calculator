# Eternity — Beta function `B(x, y)` (D3)

**Author:** Venis P.  
**Course:** SOEN 6011 — Summer 2025  
**Function:** F6 — Beta function `B(x,y)` (integer-only implementation)  
**Release:** v1.0.0  
**Repository:** https://github.com/YourRepo/ProjectEternity-BetaFunction

## Summary
This project provides a modular Java implementation of the Beta function `B(x,y)` for positive integer arguments. It includes:
- CLI (`BetaText`) and Swing GUI (`BetaGUI`)
- Core logic (`BetaCalculator`, `FactorialUtil`) using `BigInteger`/`BigDecimal`
- Unit tests (JUnit 5)
- Static analysis (Checkstyle / PMD) and debugging evidence (JDB)
- Accessibility basics (label associations, tooltips, mnemonics)
- Semantic versioning and public GitHub hosting

## Quick start
Prerequisites: Java 11+, Maven, Graphviz (for PlantUML)

Build:
```bash
mvn clean compile