# Contributing to Logify

Thank you for your interest in contributing to Logify! We welcome contributions from the community and are pleased to have them.

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Git

### Development Setup

1. **Fork the repository**
   ```bash
   # Click the "Fork" button on GitHub, then clone your fork
   git clone https://github.com/YOUR_USERNAME/Logify.git
   cd Logify
   ```

2. **Set up the upstream remote**
   ```bash
   git remote add upstream https://github.com/amitkt123/Logify.git
   ```

3. **Install dependencies**
   ```bash
   mvn clean install
   ```

4. **Run tests to ensure everything works**
   ```bash
   mvn test
   ```

## 🔧 Development Workflow

### Creating a Feature Branch

```bash
git checkout -b feature/your-feature-name
```

Use descriptive branch names:
- `feature/async-logging`
- `bugfix/null-pointer-in-sink`
- `docs/update-readme`

### Making Changes

1. **Write tests first** (TDD approach recommended)
2. **Implement your changes**
3. **Ensure all tests pass**
4. **Update documentation** if needed
5. **Follow code style guidelines**

### Code Style Guidelines

- **Java Code Style**: Follow Google Java Style Guide
- **Indentation**: 4 spaces (no tabs)
- **Line Length**: 100 characters maximum
- **Naming**: Use descriptive names for variables and methods
- **Comments**: Document public APIs and complex logic

Example:
```java
/**
 * Routes log messages to appropriate sinks based on configuration.
 */
public class SinkRouter {
    private final Map<Level, List<Sink>> levelToSinks;
    
    /**
     * Routes a message to all configured sinks for its level.
     *
     * @param message the log message to route
     * @throws RoutingException if routing fails
     */
    public void route(LogMessage message) throws RoutingException {
        // Implementation
    }
}
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=LoggerTest

# Run tests with coverage
mvn test jacoco:report
```

### Committing Changes

Write clear, descriptive commit messages:

```bash
git commit -m "feat: add async logging support

- Implement AsyncSink for non-blocking writes
- Add configuration options for buffer size and flush interval
- Include performance tests for async operations

Closes #123"
```

Commit message format:
- `feat:` new feature
- `fix:` bug fix
- `docs:` documentation changes
- `style:` formatting, missing semicolons, etc.
- `refactor:` code change that neither fixes a bug nor adds a feature
- `test:` adding missing tests
- `chore:` maintain

## 📝 Submitting Changes

### Pull Request Process

1. **Update your branch with latest changes**
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

2. **Push your changes**
   ```bash
   git push origin feature/your-feature-name
   ```

3. **Create a Pull Request**
   - Go to the GitHub repository
   - Click "New Pull Request"
   - Choose your branch
   - Fill out the PR template

### Pull Request Guidelines

- **Title**: Clear and descriptive
- **Description**: Explain what changes you made and why
- **Link Issues**: Reference related issues with "Closes #123"
- **Tests**: Include tests for new functionality
- **Documentation**: Update docs for new features
- **Breaking Changes**: Clearly mark any breaking changes

### PR Template

```markdown
## Description
Brief description of changes made.

## Type of Change
- [ ] Bug fix (non-breaking change which fixes an issue)
- [ ] New feature (non-breaking change which adds functionality)
- [ ] Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] Documentation update

## Testing
- [ ] Unit tests pass
- [ ] Integration tests pass
- [ ] Manual testing completed

## Checklist
- [ ] Code follows project style guidelines
- [ ] Self-review completed
- [ ] Comments added for hard-to-understand areas
- [ ] Documentation updated
- [ ] No new warnings introduced
```

## 🐛 Bug Reports

When filing a bug report, please include:

1. **Environment details** (Java version, OS, etc.)
2. **Steps to reproduce** the issue
3. **Expected behavior**
4. **Actual behavior**
5. **Code samples** if applicable
6. **Log output** if relevant

Use the bug report template:

```markdown
**Environment:**
- Java Version: 
- Logify Version: 
- OS: 

**Description:**
A clear description of the bug.

**Steps to Reproduce:**
1. 
2. 
3. 

**Expected Behavior:**
What you expected to happen.

**Actual Behavior:**
What actually happened.

**Code Sample:**
```java
// Your code here
```

**Additional Context:**
Any other relevant information.
```

## 💡 Feature Requests

For feature requests, please:

1. **Check existing issues** to avoid duplicates
2. **Describe the problem** you're trying to solve
3. **Propose a solution** if you have one
4. **Consider alternatives** and their trade-offs
5. **Provide use cases** and examples

## 🔍 Code Review Process

1. **Automated checks** must pass (CI, tests, linting)
2. **Maintainer review** required for all PRs
3. **Address feedback** promptly and thoroughly
4. **Squash commits** if requested before merge

## 📚 Development Resources

### Useful Commands

```bash
# Format code
mvn spotless:apply

# Check dependencies for updates
mvn versions:display-dependency-updates

# Generate site documentation
mvn site

# Run mutation tests
mvn org.pitest:pitest-maven:mutationCoverage
```

### Project Structure

```
Logify/
├── src/
│   ├── main/java/com/logify/
│   │   ├── core/           # Core logging interfaces
│   │   ├── sinks/          # Sink implementations
│   │   ├── config/         # Configuration handling
│   │   └── utils/          # Utility classes
│   └── test/java/          # Test classes
├── examples/               # Usage examples
├── docs/                   # Documentation
└── pom.xml                # Maven configuration
```

## 🏷️ Release Process

For maintainers:

1. **Update version** in `pom.xml`
2. **Update CHANGELOG.md**
3. **Create release branch**
4. **Tag release**
5. **Deploy to Maven Central**
6. **Update GitHub release notes**

## 📞 Getting Help

- **GitHub Discussions**: For questions and community support
- **GitHub Issues**: For bug reports and feature requests
- **Email**: For security-related issues (security@logify.com)

## 📄 License

By contributing to Logify, you agree that your contributions will be licensed under the GPL v3 License.

---

Thank you for contributing to Logify! 🎉