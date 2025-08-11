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

## Requirements

* **Java SE 21** or higher
* **Maven 3.9+**
* Internet connection for dependency resolution

## Installation & Usage

```bash
# Clone repository
git clone https://github.com/username/beta-function-calculator.git
cd beta-function-calculator

# Build & run tests
mvn clean install
mvn test

# Run application
mvn exec:java -Dexec.mainClass="com.example.Main"
```

## Accessibility

* Uses `setLabelFor` for component associations.
* Compatible with Java Access Bridge.
* Keyboard navigation enabled.

## Static Analysis

* **Checkstyle** with Sun conventions.
* **PMD** for code smells and unused code detection.
* **CodeQL Analysis** Security Analysis - GitHub on Actions.

## Testing

* **JUnit 5** test coverage.
* Maven Surefire plugin for HTML reports.

## Diagrams

* Class diagrams and sequence diagrams in `/docs/diagrams`.
* Generated using **PlantUML**.

## Versioning

This project follows [Semantic Versioning 2.0.0](https://semver.org/). Current release: **v1.0.0**.

## License

This project is licensed under the MIT License.

## References

1. Bloch, J. (2018). *Effective Java* (3rd ed.). Addison-Wesley. ISBN: 978-0134685991.
2. Oracle. (2025). *Java Accessibility Overview*. Retrieved from [https://docs.oracle.com/en/java/javase/21/access/java-accessibility-overview.html](https://docs.oracle.com/en/java/javase/21/access/java-accessibility-overview.html)
3. Google. (2025). *Google Java Style Guide*. Retrieved from [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html)
4. Checkstyle. (2025). *Checkstyle – Java code style checker*. Retrieved from [https://checkstyle.sourceforge.io/](https://checkstyle.sourceforge.io/)
5. PMD. (2025). *PMD – Source Code Analyzer*. Retrieved from [https://pmd.github.io/](https://pmd.github.io/)
6. SonarSource. (2025). *SonarLint – Clean Code in your IDE*. Retrieved from [https://www.sonarlint.org/](https://www.sonarlint.org/)
7. Oracle. (2025). *Java Platform Debugger Architecture (JPDA)*. Retrieved from [https://docs.oracle.com/javase/8/docs/technotes/guides/jpda/](https://docs.oracle.com/javase/8/docs/technotes/guides/jpda/)
8. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley. ISBN: 978-0201633610.
9. Beck, K., & Gamma, E. (2004). *JUnit Pocket Guide*. O’Reilly Media. ISBN: 978-0596007430.

