# Selenium Demo Framework

A compact, production-style test automation framework in **Java + Selenium +
TestNG** — UI tests (Page Object Model) plus REST Assured API tests, running in
CI on every push.

This repo is a **sample of my Test Automation Foundation work**: the kind of
foundation I build for startups in 4–6 weeks — strategy, framework, CI
integration, and handover docs.

## What's inside

```
src/test/java/com/demo/
  pages/        Page Objects (selectors + flows live here, not in tests)
  tests/        UI specs (TestNG) against the SauceDemo demo store
  api/          REST Assured API specs against the JSONPlaceholder demo API
  base/         BaseTest — fresh browser per test, always quit afterwards
  utils/        DriverFactory — WebDriverManager, headless by default
src/test/resources/testng.xml   Suite definition (parallel methods)
.github/workflows/ci.yml        GitHub Actions: JDK 17 → mvn test → reports
```

## Design choices

- **Page Object Model** — a UI change means editing one page class, not ten tests.
- **data-test selectors** — resilient locators, no brittle XPath.
- **WebDriverManager** — no driver binaries checked into the repo.
- **Explicit waits** — no `Thread.sleep`, no flaky timing.
- **UI and API suites in one run** — `mvn test` runs everything via testng.xml.
- **CI-first** — headless Chrome on GitHub Actions, TestNG reports as artifacts.

## Run it locally

Requires JDK 17+ and Maven.

```bash
mvn test                      # everything (headless)
mvn test -Dheadless=false     # headed, watch it run
```

Reports: `target/surefire-reports/`

## About

Built by Ruslan Huseynov — Senior SDET (14+ years). I help startups ship with
confidence: [Test Automation Foundation](https://www.linkedin.com/in/ruslan-huseynov-386b07308)
in 4–6 weeks.
