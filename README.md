# Example Maven Project

This project is used for demonstration of [yamory](https://yamory.io).
Please enjoy hunting vulns!

## Requirements

- Java 11+
- Maven 3+

## Dependency tree

### Root project (aggregator)

```
./mvnw dependency:tree -B -Dverbose=true
```

### common-subproject (independent Maven root)

```
cd common-subproject
./mvnw dependency:tree -B -Dverbose=true
```

Uses the wrapper installed inside `common-subproject/`. Prints the tree for `common-subproject` only, without pulling in the parent aggregator.
