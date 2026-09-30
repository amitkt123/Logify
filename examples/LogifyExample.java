package com.logify.examples;

import com.logify.Logger;
import com.logify.LoggerFactory;
import com.logify.config.LoggerConfig;

/**
 * Example usage of the Logify logging library
 * 
 * This example demonstrates:
 * - Basic logger configuration
 * - Different log levels
 * - Namespace usage
 * - Exception logging
 * - Parameterized messages
 */
public class LogifyExample {
    
    public static void main(String[] args) {
        try {
            // Initialize logger with configuration file
            LoggerConfig config = LoggerConfig.fromFile("examples/basic-config.json");
            Logger logger = LoggerFactory.getLogger("ExampleApp", config);
            
            // Basic logging examples
            demonstrateBasicLogging(logger);
            
            // Namespace organization
            demonstrateNamespaces();
            
            // Exception handling
            demonstrateExceptionLogging(logger);
            
            // Performance considerations
            demonstrateConditionalLogging(logger);
            
        } catch (Exception e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void demonstrateBasicLogging(Logger logger) {
        System.out.println("=== Basic Logging Demo ===");
        
        logger.debug("Application starting up", "app.startup");
        logger.info("Configuration loaded successfully", "app.config");
        logger.warn("Using default database connection pool size", "db.config");
        logger.error("Failed to connect to external service", "external.api");
        logger.fatal("Critical system failure - shutting down", "system.critical");
    }
    
    private static void demonstrateNamespaces() {
        System.out.println("\n=== Namespace Organization Demo ===");
        
        // Different loggers for different components
        Logger authLogger = LoggerFactory.getLogger("AuthService");
        Logger dbLogger = LoggerFactory.getLogger("DatabaseService");
        Logger apiLogger = LoggerFactory.getLogger("APIGateway");
        
        authLogger.info("User login successful: {}", "user123", "auth.login");
        dbLogger.warn("Connection pool 80% utilized", "db.pool.warning");
        apiLogger.debug("Processing request: {}", "/api/users/123", "api.request");
    }
    
    private static void demonstrateExceptionLogging(Logger logger) {
        System.out.println("\n=== Exception Logging Demo ===");
        
        try {
            // Simulate an operation that might fail
            simulateRiskyOperation();
        } catch (RuntimeException e) {
            logger.error("Operation failed with exception: {}", 
                        e.getMessage(), "operation.error", e);
        }
    }
    
    private static void demonstrateConditionalLogging(Logger logger) {
        System.out.println("\n=== Conditional Logging Demo ===");
        
        // Only generate expensive debug info if debug logging is enabled
        if (logger.isDebugEnabled()) {
            String expensiveDebugInfo = generateExpensiveDebugInformation();
            logger.debug("Detailed system state: {}", expensiveDebugInfo, "system.debug");
        }
        
        // Check other levels
        if (logger.isInfoEnabled()) {
            logger.info("Info level logging is enabled", "system.info");
        }
    }
    
    private static void simulateRiskyOperation() {
        throw new RuntimeException("Simulated failure in risky operation");
    }
    
    private static String generateExpensiveDebugInformation() {
        // Simulate expensive operation
        return "Memory: 512MB, CPU: 45%, Threads: 23, Connections: 12";
    }
}