# Logify 📋

A powerful, flexible, and configurable logging library designed for modern applications. Logify provides a robust message routing system that directs log messages to appropriate sinks based on configurable levels and namespaces.

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()
[![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)]()

## 🚀 Features

- **Multi-level Message Routing**: Supports FATAL, ERROR, WARN, INFO, and DEBUG levels with priority-based filtering
- **Flexible Sink System**: Route messages to multiple destinations (files, console, databases, etc.)
- **Namespace Support**: Organize logs by application components or modules
- **Automatic Message Enrichment**: Adds timestamps and metadata to log messages
- **Configurable Architecture**: JSON/YAML-based configuration for easy setup
- **Level-based Filtering**: Automatically logs messages at or above configured levels
- **Multiple Sink Types**: Built-in support for various output destinations

## 📋 Table of Contents

- [Installation](#installation)
- [Quick Start](#quick-start)
- [Configuration](#configuration)
- [Message Levels](#message-levels)
- [Sink Types](#sink-types)
- [Usage Examples](#usage-examples)
- [API Reference](#api-reference)
- [Advanced Configuration](#advanced-configuration)
- [Contributing](#contributing)
- [License](#license)

## 🔧 Installation

### Maven
```xml
<dependency>
    <groupId>com.logify</groupId>
    <artifactId>logify-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
```gradle
implementation 'com.logify:logify-core:1.0.0'
```

### Manual Installation
Download the latest JAR from [releases](https://github.com/amitkt123/Logify/releases) and add to your classpath.

## ⚡ Quick Start

```java
import com.logify.Logger;
import com.logify.LoggerFactory;
import com.logify.config.LoggerConfig;

// Initialize logger with configuration
LoggerConfig config = LoggerConfig.fromFile("logify-config.json");
Logger logger = LoggerFactory.getLogger("MyApplication", config);

// Log messages
logger.debug("Application started", "app.startup");
logger.info("User logged in: {}", username, "auth.login");
logger.warn("High memory usage detected", "system.performance");
logger.error("Database connection failed", "db.connection");
logger.fatal("Critical system failure", "system.critical");
```

## ⚙️ Configuration

Logify uses a flexible configuration system that defines how messages are routed to different sinks based on their levels.

### Basic Configuration Structure

```json
{
  "timeFormat": "yyyy-MM-dd HH:mm:ss.SSS",
  "configurations": [
    {
      "levels": ["FATAL", "ERROR"],
      "sink": {
        "type": "FILE",
        "location": "/var/log/app/errors.log",
        "format": "[{timestamp}] [{level}] [{namespace}] {message}"
      }
    },
    {
      "levels": ["WARN", "INFO"],
      "sink": {
        "type": "CONSOLE",
        "format": "{timestamp} - {level}: {message}"
      }
    },
    {
      "levels": ["DEBUG"],
      "sink": {
        "type": "FILE",
        "location": "/var/log/app/debug.log",
        "format": "[{timestamp}] DEBUG [{namespace}] {message}"
      }
    }
  ]
}
```

### Configuration Properties

| Property | Description | Required | Default |
|----------|-------------|----------|---------|
| `timeFormat` | Timestamp format pattern | No | `yyyy-MM-dd HH:mm:ss` |
| `configurations` | Array of level-to-sink mappings | Yes | - |
| `configurations[].levels` | Message levels for this configuration | Yes | - |
| `configurations[].sink` | Sink configuration object | Yes | - |

## 📊 Message Levels

Logify supports five message levels in order of priority:

| Level | Priority | Description | Use Case |
|-------|----------|-------------|----------|
| `FATAL` | 1 (Highest) | System-ending errors | Critical failures that require immediate attention |
| `ERROR` | 2 | Application errors | Exceptions, failed operations |
| `WARN` | 3 | Warning conditions | Potential issues, deprecation notices |
| `INFO` | 4 | Informational messages | General application flow, user actions |
| `DEBUG` | 5 (Lowest) | Debug information | Detailed diagnostic information |

### Level Filtering Rules

When a level is configured, **all messages at that level and above are logged**:

- If `INFO` is configured → `FATAL`, `ERROR`, `WARN`, and `INFO` messages are logged
- If `ERROR` is configured → Only `FATAL` and `ERROR` messages are logged
- If `DEBUG` is configured → All message levels are logged

## 🎯 Sink Types

### Console Sink
Outputs messages to standard output/error streams.

```json
{
  "type": "CONSOLE",
  "stream": "stdout",
  "format": "{timestamp} [{level}] {message}",
  "colorEnabled": true
}
```

### File Sink
Writes messages to log files with rotation support.

```json
{
  "type": "FILE",
  "location": "/var/log/app/application.log",
  "format": "[{timestamp}] [{level}] [{namespace}] {message}",
  "maxSize": "10MB",
  "maxFiles": 5,
  "append": true
}
```

### Database Sink
Stores messages in database tables.

```json
{
  "type": "DATABASE",
  "connectionString": "jdbc:postgresql://localhost:5432/logs",
  "table": "application_logs",
  "columns": {
    "timestamp": "log_time",
    "level": "log_level",
    "namespace": "component",
    "message": "log_message"
  }
}
```

### Custom Sink
Implement your own sink by extending the `Sink` interface.

```java
public class CustomSink implements Sink {
    @Override
    public void write(LogMessage message) {
        // Custom implementation
    }
}
```

## 💡 Usage Examples

### Basic Logging

```java
Logger logger = LoggerFactory.getLogger("OrderService");

// Simple messages
logger.info("Processing order", "order.processing");
logger.error("Payment failed", "payment.error");

// Parameterized messages
logger.info("User {} placed order {}", userId, orderId, "order.placed");
logger.warn("Inventory low for product {}: {} remaining", 
           productId, quantity, "inventory.warning");
```

### Namespace Organization

```java
// Different components using different namespaces
Logger authLogger = LoggerFactory.getLogger("AuthService");
Logger dbLogger = LoggerFactory.getLogger("DatabaseService");
Logger apiLogger = LoggerFactory.getLogger("APIGateway");

authLogger.info("User authentication successful", "auth.success");
dbLogger.error("Connection pool exhausted", "db.pool.error");
apiLogger.debug("Request received: {}", requestUrl, "api.request");
```

### Exception Logging

```java
try {
    // Risky operation
    processPayment(order);
} catch (PaymentException e) {
    logger.error("Payment processing failed for order {}: {}", 
                 order.getId(), e.getMessage(), "payment.error", e);
}
```

### Conditional Logging

```java
// Only log debug messages if debug level is enabled
if (logger.isDebugEnabled()) {
    String expensiveDebugInfo = generateDetailedDiagnostics();
    logger.debug("Detailed diagnostics: {}", expensiveDebugInfo, "system.diagnostics");
}
```

## 📚 API Reference

### Logger Interface

```java
public interface Logger {
    void fatal(String message, String namespace);
    void fatal(String message, String namespace, Throwable throwable);
    void fatal(String messagePattern, Object[] args, String namespace);
    
    void error(String message, String namespace);
    void error(String message, String namespace, Throwable throwable);
    void error(String messagePattern, Object[] args, String namespace);
    
    void warn(String message, String namespace);
    void warn(String messagePattern, Object[] args, String namespace);
    
    void info(String message, String namespace);
    void info(String messagePattern, Object[] args, String namespace);
    
    void debug(String message, String namespace);
    void debug(String messagePattern, Object[] args, String namespace);
    
    boolean isDebugEnabled();
    boolean isInfoEnabled();
    boolean isWarnEnabled();
    boolean isErrorEnabled();
    boolean isFatalEnabled();
}
```

### LogMessage Structure

```java
public class LogMessage {
    private final String content;
    private final Level level;
    private final String namespace;
    private final LocalDateTime timestamp;
    private final Throwable throwable;
    private final Map<String, Object> metadata;
    
    // Getters and utility methods
}
```

### Configuration Classes

```java
public class LoggerConfig {
    public static LoggerConfig fromFile(String configPath);
    public static LoggerConfig fromJson(String json);
    public static LoggerConfig fromYaml(String yaml);
    
    public List<SinkConfiguration> getConfigurations();
    public String getTimeFormat();
}
```

## 🔧 Advanced Configuration

### Multiple Environments

```json
{
  "profiles": {
    "development": {
      "timeFormat": "HH:mm:ss.SSS",
      "configurations": [
        {
          "levels": ["DEBUG", "INFO", "WARN", "ERROR", "FATAL"],
          "sink": {
            "type": "CONSOLE",
            "colorEnabled": true
          }
        }
      ]
    },
    "production": {
      "timeFormat": "yyyy-MM-dd HH:mm:ss.SSS",
      "configurations": [
        {
          "levels": ["ERROR", "FATAL"],
          "sink": {
            "type": "FILE",
            "location": "/var/log/app/errors.log"
          }
        },
        {
          "levels": ["INFO", "WARN"],
          "sink": {
            "type": "DATABASE",
            "connectionString": "jdbc:postgresql://prod-db:5432/logs"
          }
        }
      ]
    }
  }
}
```

### Message Formatting

Available placeholders in format strings:

- `{timestamp}` - Message timestamp
- `{level}` - Log level
- `{namespace}` - Message namespace
- `{message}` - Log message content
- `{thread}` - Thread name
- `{class}` - Calling class name
- `{method}` - Calling method name
- `{line}` - Line number

### Performance Tuning

```json
{
  "performance": {
    "asyncLogging": true,
    "bufferSize": 8192,
    "flushInterval": 1000,
    "queueSize": 10000
  }
}
```

## 🏗️ Architecture

### Core Components

1. **Logger**: Main interface for logging messages
2. **LoggerFactory**: Creates and manages logger instances
3. **LogMessage**: Represents a log message with metadata
4. **Sink**: Destination for log messages
5. **SinkRouter**: Routes messages to appropriate sinks
6. **Configuration**: Manages logging configuration

### Message Flow

```
Application → Logger → LogMessage → SinkRouter → Sink(s) → Destination
```

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

### Development Setup

1. Clone the repository
2. Install dependencies: `mvn install`
3. Run tests: `mvn test`
4. Build: `mvn package`

### Submitting Changes

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## 📝 License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

- **Documentation**: [Wiki](https://github.com/amitkt123/Logify/wiki)
- **Issues**: [GitHub Issues](https://github.com/amitkt123/Logify/issues)
- **Discussions**: [GitHub Discussions](https://github.com/amitkt123/Logify/discussions)

## 🚧 Roadmap

- [ ] Async logging support
- [ ] Cloud sink integrations (AWS CloudWatch, Azure Monitor)
- [ ] Metrics and monitoring integration
- [ ] Configuration hot-reloading
- [ ] Custom message formatters
- [ ] Log message filtering and sampling

---

**Made with ❤️ by the Logify team**
