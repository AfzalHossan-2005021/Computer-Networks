# 🌐 Socket Programming - HTTP Server & Client

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Networking](https://img.shields.io/badge/Networking-Socket%20Programming-brightgreen?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

A robust, multi-threaded HTTP server and client implementation in Java with advanced socket programming concepts. Perfect for understanding network communication fundamentals.

</div>

---

## 📋 Table of Contents

- [Overview](#Overview)
- [Features](#Features)
- [Architecture](#Architecture)
- [System Requirements](#system-requirements)
- [Installation & Setup](#installation--setup)
- [Usage Guide](#usage-guide)
- [Project Structure](#project-structure)
- [Technical Specifications](#technical-specifications)
- [API Reference](#api-reference)
- [Contributing](#contributing)
- [Author](#author)
- [License](#license)

---

## Overview

**Socket Programming** is a comprehensive Java implementation of an HTTP server and client using low-level socket programming. This project demonstrates essential network communication concepts including:

- **Multi-threaded Server Architecture** - Handling multiple concurrent client connections
- **File Transfer Protocol** - Uploading and downloading files with MIME type detection
- **Socket Communication** - Direct TCP/IP socket manipulation
- **Logging System** - Request/response tracking and analysis

Perfect for computer science students and developers learning network programming fundamentals.

---

## ✨ Features

### 🖥️ Server Features
- **Multi-threaded Architecture** - Concurrent handling of multiple clients using Java threads
- **File Serving** - Serve static files (HTML, TXT, images, etc.) from the public directory
- **File Upload** - Receive and store uploaded files with MIME type support
- **Chunked Transfer** - Efficient file transmission using 1024-byte chunks
- **Request Logging** - Comprehensive logging of all client requests and responses
- **Directory Management** - Automatic creation of public directory structure
- **Custom Protocol** - HTTP-like protocol for request/response handling

### 👨‍💻 Client Features
- **Interactive CLI** - Command-line interface for sending requests
- **File Upload** - Upload files to the server with automatic MIME type detection
- **File Download** - Download files from the server
- **Batch Operations** - Support for multiple concurrent requests
- **Base64 Encoding** - Secure data transmission with encoding support
- **Connection Management** - Automatic connection pooling and management

### 🔧 General Features
- **Cross-platform Compatibility** - Works on Windows, macOS, and Linux
- **Minimal Dependencies** - Pure Java with no external libraries required
- **Well-documented Code** - Clear, readable, and maintainable source code
- **Error Handling** - Comprehensive exception handling and error reporting

---

## 🏗️ Architecture

### System Design

```
┌─────────────────────────────────────────────────────────────┐
│                      CLIENT SIDE                            │
│  ┌────────────────────────────────────────────────────────┐ │
│  │ Client (Main Entry Point)                              │ │
│  │ - Interactive command prompt                           │ │
│  │ - User input handling                                  │ │
│  └────────────────────────────────────────────────────────┘ │
│                            ↓                                │
│  ┌────────────────────────────────────────────────────────┐ │
│  │ RequestHandler (Threaded)                              │ │
│  │ - Parse user commands (UPLOAD/DOWNLOAD)                │ │
│  │ - Establish socket connections                         │ │
│  │ - Send/receive file data                               │ │
│  └────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
                            ↕ (TCP/IP)
┌─────────────────────────────────────────────────────────────┐
│                      SERVER SIDE                            │
│  ┌────────────────────────────────────────────────────────┐ │
│  │ HTTPServer (Main Entry Point)                          │ │
│  │ - ServerSocket on port 5021                            │ │
│  │ - Accept incoming connections                          │ │
│  │ - Spawn threads for each client                        │ │
│  └────────────────────────────────────────────────────────┘ │
│                            ↓                                │
│  ┌────────────────────────────────────────────────────────┐ │
│  │ ReadWriteThread (Per-Client Handler)                   │ │
│  │ - Parse incoming requests                              │ │
│  │ - Execute file operations                              │ │
│  │ - Send responses                                       │ │
│  └────────────────────────────────────────────────────────┘ │
│                            ↓                                │
│  ┌────────────────────────────────────────────────────────┐ │
│  │ SocketWrapper (Socket I/O Abstraction)                 │ │
│  │ - Buffered reading/writing                             │ │
│  │ - Logging to file                                      │ │
│  │ - Stream management                                    │ │
│  └────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

---

## System Requirements

| Requirement | Specification |
|-------------|---------------|
| **Java Version** | JDK 8 or higher |
| **Operating System** | Windows, macOS, Linux |
| **Network** | TCP/IP enabled |
| **RAM** | Minimum 256 MB |
| **Disk Space** | Minimum 100 MB |

---

## Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or later installed
- `javac` and `java` commands accessible from command line

### Step 1: Clone or Download
```bash
cd /path/to/Socket-Programing
```

### Step 2: Compile the Project
```bash
# Compile all Java files
javac -d out src/Server/*.java src/Client/*.java
```

### Step 3: Create Directory Structure
```bash
# Create the public directory for file serving
mkdir Public
```

### Step 4: Run the Server
```bash
java -cp out Server.HTTPServer
```

Expected output:
```
Server started.
Listening for connections on port : 5021 ...
```

### Step 5: Run the Client (in another terminal)
```bash
java -cp out Client.Client
```

---

## Usage Guide

### Server Startup

```bash
java -cp out Server.HTTPServer
```

The server will:
- Create a `Public` directory if it doesn't exist
- Listen on **port 5021**
- Accept incoming client connections
- Log all activities to `log.txt`

### Client Commands

The client supports the following commands:

#### 1. **Upload a File**
```bash
UPLOAD /path/to/filename.txt
```
- Sends the file to the server
- Stores it in the `Public` directory
- Automatically detects MIME type
- Shows progress and status

#### 2. **Download a File**
```bash
DOWNLOAD filename.txt
```
- Retrieves file from server's `Public` directory
- Saves to current working directory
- Shows download progress

#### 3. **Exit Client**
```bash
exit
```
- Gracefully closes the client connection
- Terminates the application

### Example Session

**Server Terminal:**
```
Server started.
Listening for connections on port : 5021 ...
```

**Client Terminal:**
```
> UPLOAD /home/user/document.pdf
[✓] File uploaded successfully
[✓] Status: 200 OK

> DOWNLOAD document.pdf
[✓] File downloaded successfully

> exit
[✓] Connection closed
```

---

## Project Structure

```
Socket-Programing/
├── README.md                          # This file
├── LICENSE                            # License file
├── Socket-Programing.iml              # IntelliJ IDEA project file
├── .gitignore                         # Git ignore rules
├── .idea/                             # IntelliJ IDEA configuration
│   ├── .gitignore
│   ├── .name
│   ├── misc.xml
│   ├── modules.xml
│   ├── vcs.xml
│   └── inspectionProfiles/
│       └── Project_Default.xml
└── src/
    ├── Server/                        # Server-side implementation
    │   ├── HTTPServer.java            # Main server class
    │   │   └── Responsibilities:
    │   │       • Create ServerSocket on port 5021
    │   │       • Accept client connections
    │   │       • Spawn ReadWriteThread for each client
    │   │       • Manage public directory
    │   │
    │   ├── ReadWriteThread.java       # Client handler thread
    │   │   └── Responsibilities:
    │   │       • Parse HTTP-like requests
    │   │       • Handle file uploads/downloads
    │   │       • Execute file operations
    │   │       • Send HTTP responses
    │   │       • Log all activities
    │   │
    │   └── SocketWrapper.java         # Socket I/O wrapper
    │       └── Responsibilities:
    │           • Abstract socket I/O operations
    │           • Manage buffered readers/writers
    │           • Handle file logging
    │           • Provide utility methods
    │
    └── Client/                        # Client-side implementation
        ├── Client.java                # Main client class
        │   └── Responsibilities:
        │       • Display interactive prompt
        │       • Accept user commands
        │       • Validate input
        │       • Spawn RequestHandler threads
        │
        └── RequestHandler.java        # Request processing thread
            └── Responsibilities:
                • Connect to server
                • Parse user commands
                • Handle file upload/download
                • Stream file data
                • Manage connections
```

---

## Technical Specifications

### Server Configuration

| Property | Value | Purpose |
|----------|-------|---------|
| **Service Port** | `5021` | TCP socket listening port |
| **Root Directory** | `Public/` | Base directory for file serving |
| **Chunk Size** | `1024 bytes` | File transfer buffer size |
| **Log File** | `log.txt` | Request/response log storage |
| **Threading Model** | One thread per client | Concurrent connection handling |

### Protocol Specification

#### UPLOAD Request Format
```
UPLOAD filename.ext
MIME/type
[file binary data]
```

#### DOWNLOAD Request Format
```
GET /filename.ext HTTP/1.1
Host: localhost:5021
```

#### Response Codes
| Code | Meaning |
|------|---------|
| `200` | OK / File transferred successfully |
| `404` | File not found |
| `400` | Bad request |
| `500` | Server error |

### Data Transfer

- **Maximum Chunk Size**: 1024 bytes
- **Encoding**: Base64 for sensitive data
- **MIME Type Detection**: Automatic based on file extension
- **Buffering**: Buffered streams for efficiency

---

## API Reference

### HTTPServer
```java
public class HTTPServer {
    static final int PORT = 5021;           // Server listening port
    static final String ROOT = "Public";    // Root serving directory
    static final int CHUNK_SIZE = 1024;     // File chunk size
    static final String LOG_FILE_PATH = "log.txt";  // Log file path
    
    public static void main(String[] args); // Server entry point
}
```

### ReadWriteThread
```java
public class ReadWriteThread implements Runnable {
    private final Socket client;  // Client socket connection
    
    public ReadWriteThread(Socket client);  // Constructor
    
    @Override
    public void run();  // Thread execution
    
    String handleTxtFile(String relativePath);   // Text file handler
    // ... other file type handlers
}
```

### Client
```java
public class Client {
    public static void main(String[] args);  // Client entry point
    // Interactive command loop
}
```

### RequestHandler
```java
public class RequestHandler implements Runnable {
    static final String HOST = "localhost";      // Server host
    static final int PORT = 5021;                // Server port
    static final int CHUNK_SIZE = 1024;          // Transfer chunk size
    
    public RequestHandler(String request);  // Constructor
    
    @Override
    public void run();  // Process request
    
    private void writeLine(String line, BufferedWriter bw);  // Send line
}
```

### SocketWrapper
```java
public class SocketWrapper {
    public SocketWrapper(Socket socket) throws IOException;  // Constructor
    
    public String read() throws IOException;           // Read line
    public void write(String str);                     // Write data
    public void writeLine(String str) throws IOException;  // Write line
    public void flush();                               // Flush buffer
    public void writeLog(String str, boolean addNewLine);  // Write log
}
```

---

## Contributing

Contributions are welcome! To contribute:

1. **Report Issues** - Found a bug? Open an issue with details
2. **Suggest Features** - Have an idea? Create a feature request
3. **Submit Pull Requests** - Follow the coding standards and submit PRs
4. **Code Reviews** - Review and provide feedback on other contributions

### Coding Standards
- Follow Java naming conventions (camelCase for variables/methods, PascalCase for classes)
- Add comments for complex logic
- Keep methods focused and modular
- Add error handling for exception cases

---

## Author

**By**: Afzal (Student ID: 2005021)  
**Institution**: [Computer Networks Course]  
**Project Type**: Educational Implementation  
**Date**: 2026

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Additional Resources

### Learning Resources
- [Java Socket Programming](https://docs.oracle.com/javase/tutorial/networking/)
- [HTTP Protocol Basics](https://developer.mozilla.org/en-US/docs/Web/HTTP)
- [Multithreading in Java](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [File I/O in Java](https://docs.oracle.com/javase/tutorial/essential/io/)

### Related Topics
- Socket programming fundamentals
- Network protocols (TCP/IP)
- HTTP protocol implementation
- Multithreading and concurrency
- File handling and I/O streams
- MIME type detection

---

<div align="center">

### ⭐ If you found this project helpful, please star it! ⭐

**Happy Coding! 🚀**

</div>
