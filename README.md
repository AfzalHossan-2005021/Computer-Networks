# 🌐 Computer Networks - Comprehensive Course Projects

<div align="center">

![BUET](https://img.shields.io/badge/BUET-Computer%20Science-blue?style=for-the-badge&logo=education)
![Courses](https://img.shields.io/badge/CSE321%20%26%20CSE322-Level%203%20Term%202-brightgreen?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Complete-success?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-black?style=for-the-badge)

A comprehensive collection of Computer Networks course projects covering socket programming, routing protocols, transport layer analysis, and Cisco network configuration. Hands-on implementations demonstrating network fundamentals from application layer to physical layer.

[🔗 Quick Navigation](#-projects) • [📚 Learning Path](#-learning-path) • [🚀 Getting Started](#-getting-started)

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [🎓 Course Information](#-course-information)
- [📦 Projects](#-projects)
- [🏗️ Architecture](#️-architecture)
- [📚 Learning Path](#-learning-path)
- [🚀 Getting Started](#-getting-started)
- [Technology Stack](#technology-stack)
- [Installation Guide](#installation-guide)
- [Project Highlights](#project-highlights)
- [Key Concepts Covered](#key-concepts-covered)
- [Learning Outcomes](#learning-outcomes)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This repository contains a comprehensive collection of practical network programming and simulation projects developed as part of the **Computer Networks** course (CSE321 & CSE322) at Bangladesh University of Engineering and Technology (BUET). The projects progress from fundamental socket programming through advanced routing algorithms, congestion control mechanisms, and enterprise network configuration.

### Repository Highlights
✨ **5 Major Projects** covering all OSI layers  
✨ **2000+ Lines** of production-grade code  
✨ **17 TCP Variants** analyzed and compared  
✨ **15+ Cisco Commands** implemented and tested  
✨ **Multi-threaded Architecture** for concurrent connections  
✨ **Statistical Analysis** of network performance  

---

## 🎓 Course Information

| Course | Code | Level | Term | Institution |
|--------|------|-------|------|-------------|
| **Computer Networks 1** | CSE321 | Level 3 | Term II | Bangladesh University of Engineering and Technology (BUET) |
| **Computer Networks 2** | CSE322 | Level 3 | Term II | Bangladesh University of Engineering and Technology (BUET) |

### Course Focus Areas
- Application Layer protocols and socket programming
- Network routing and addressing
- Transport layer congestion control
- Wireless networking and MANETs
- Enterprise network design and security
- Network simulation and analysis

---

## 📦 Projects

### 1. 🔗 [Socket Programming](Socket-Programing)
**Multi-threaded HTTP Server and Client Implementation**

A robust, production-grade implementation of an HTTP server and client using low-level socket programming in Java. Demonstrates concurrent connection handling, file transfer protocols, and network I/O operations.

**Key Features:**
- Multi-threaded server handling simultaneous clients
- Custom HTTP-like protocol implementation
- File upload/download functionality with MIME type detection
- Chunked file transfer (1KB chunks)
- Comprehensive request/response logging
- Base64 encoding for secure data transmission

**Technologies:** Java, TCP/IP Sockets, Multi-threading  
**Concepts:** Client-Server Architecture, Socket Programming, I/O Streams  
**Status:** ✅ Complete with comprehensive README  
[→ Explore Project](Socket-Programing/README.md)

---

### 2. 🌐 [Online Networking Lab](Online)
**Cisco Network Configuration with VLANs, Routing, and Security**

A comprehensive Cisco networking lab implementing advanced network segmentation, inter-VLAN routing, access control, and network address translation. Uses Cisco Packet Tracer for simulation.

**Key Features:**
- **4 Virtual LANs** (Civil, MME, WRE, Management)
- **Router-on-a-Stick** inter-VLAN routing architecture
- **3 Access Control Lists** for traffic filtering
- **Network Address Translation (NAT)** configuration
- **Static routing** for multi-router environments
- **Real-world enterprise network** topologies

**Technologies:** Cisco IOS, Packet Tracer, Networking  
**Concepts:** VLANs, Inter-VLAN Routing, ACLs, NAT, Static Routing  
**Status:** ✅ Complete with advanced documentation  
[→ Explore Project](Online/README.md)

---

### 3. 🛣️ [MANET Routing Protocol Comparison](Routing-Experiment)
**AODV vs RAODV Analysis in Mobile Ad Hoc Networks**

Advanced network simulation project comparing AODV (standard) and RAODV (revised) routing protocols in mobile ad hoc network scenarios. Uses NS-3 simulator for detailed performance analysis.

**Key Features:**
- **17 Routing Protocols** supported (AODV, OLSR, DSDV, etc.)
- **50+ Node Networks** with mobility models
- **Comprehensive Performance Metrics:**
  - Network throughput
  - End-to-end delay
  - Packet delivery ratio
  - Routing overhead analysis
- **Visual Performance Comparison** using Matplotlib
- **Speed and Network Density** impact analysis
- **CSV Data Export** for external analysis

**Technologies:** NS-3 Simulator, C++, Python, Matplotlib  
**Concepts:** MANET Protocols, Routing Algorithms, Network Simulation  
**Status:** ✅ Complete with detailed research documentation  
[→ Explore Project](Routing-Experiment/README.md)

---

### 4. 🚀 [TCP Congestion Control Analysis](Transport%20-Layer-Experiment)
**Comprehensive TCP Variant Comparison and Transport Layer Study**

In-depth analysis of TCP congestion control mechanisms comparing 17 different TCP variants under various network conditions. Detailed packet-level tracing and visualization of TCP state evolution.

**Key Features:**
- **17 TCP Variants Analyzed:**
  - Classic (Tahoe, Reno, NewReno, Vegas)
  - High-Speed (HighSpeed, Scalable, Fast, Illinois)
  - Modern (CUBIC, BBR, DCTCP)
  - Hybrid (Westwood+, Veno, Hybla)
  - Specialized (Ledbat, LP)
- **Two-Task Experiments:**
  - Task 1: Protocol performance comparison
  - Task 2: Detailed state analysis
- **7 TCP Metrics Traced:**
  - Congestion Window (CWND)
  - Slow Start Threshold (SSTH)
  - Round-Trip Time (RTT)
  - Retransmission Timeout (RTO)
  - In-Flight Bytes
  - Sequence Numbers (TX/RX)
- **Multi-Flow Analysis** for fairness evaluation
- **Automatic Parameter Assignment** based on student ID

**Technologies:** NS-3 Simulator, C++, Python (Pandas, Matplotlib)  
**Concepts:** TCP Protocol, Congestion Control, Performance Metrics  
**Status:** ✅ Complete with visualization and analysis tools  
[→ Explore Project](Transport%20-Layer-Experiment/README.md)

---

### 5. 🔧 [Network Configuration](Network-Configuration)
**Cisco Network Design and Implementation**

Practical Cisco network configuration including device setup, routing configuration, and network connectivity. Based on real-world enterprise networking scenarios.

**Key Features:**
- Cisco switch and router configuration
- Network device management
- Routing protocol setup
- Physical and logical topology design
- Configuration cheatsheets
- Packet Tracer simulation files

**Technologies:** Cisco IOS, Packet Tracer, Network Design  
**Concepts:** Network Configuration, Device Management, Topology Design  
**Status:** ✅ Configured and documented  

---

## 🏗️ Architecture

### Project Hierarchy & Dependencies

```
Computer-Networks/
│
├── 1. Socket-Programing (Application Layer)
│   ├─ Java socket-based communication
│   ├─ HTTP-like protocol implementation
│   └─ File transfer mechanisms
│
├── 2. Online (Network + Data Link Layer)
│   ├─ Cisco IOS configuration
│   ├─ VLAN segmentation (Layer 2)
│   ├─ IP routing (Layer 3)
│   └─ ACL security policies
│
├── 3. Transport -Layer-Experiment (Transport Layer)
│   ├─ TCP protocol analysis
│   ├─ Congestion control algorithms
│   ├─ Flow control mechanisms
│   └─ Performance under varying conditions
│
├── 4. Routing-Experiment (Network Layer)
│   ├─ Routing protocol design
│   ├─ Mobile network scenarios
│   ├─ Protocol comparison methodology
│   └─ Performance evaluation
│
└── 5. Network-Configuration (Multi-layer)
    ├─ Practical configuration
    ├─ Real-world topologies
    └─ Enterprise design patterns
```

### OSI Layer Coverage

```
┌─────────────────────────────────────────────────────────────┐
│ 7. Application Layer    │ Socket Programming (HTTP Protocol) │
├─────────────────────────┼───────────────────────────────────┤
│ 6. Presentation Layer   │ Data Encoding & Serialization     │
├─────────────────────────┼───────────────────────────────────┤
│ 5. Session Layer        │ Connection Management             │
├─────────────────────────┼───────────────────────────────────┤
│ 4. Transport Layer      │ TCP Congestion Control Analysis   │
├─────────────────────────┼───────────────────────────────────┤
│ 3. Network Layer        │ Routing (AODV, RAODV)             │
├─────────────────────────┼───────────────────────────────────┤
│ 2. Data Link Layer      │ VLANs, Switching, Physical Config │
├─────────────────────────┼───────────────────────────────────┤
│ 1. Physical Layer       │ Network Topology & Cabling        │
└─────────────────────────┴───────────────────────────────────┘
```

---

## 📚 Learning Path

### Recommended Study Sequence

```
Foundation (Week 1-2)
    ↓
1. Online Networking Lab
   └─ Understand basic networking concepts
   └─ Learn Cisco IOS fundamentals
   └─ Network design principles
    ↓
Application Layer (Week 3-4)
    ↓
2. Socket Programming
   └─ Understand client-server architecture
   └─ Learn socket programming concepts
   └─ Multi-threading and concurrent programming
    ↓
Network Layer (Week 5-7)
    ↓
3. MANET Routing Experiment
   └─ Understand routing protocols
   └─ Learn network simulation with NS-3
   └─ Protocol comparison methodology
    ↓
Transport Layer (Week 8-10)
    ↓
4. TCP Congestion Control Analysis
   └─ Deep dive into TCP mechanics
   └─ Understand congestion control algorithms
   └─ Performance analysis and optimization
    ↓
Practical Integration (Week 11-12)
    ↓
5. Network Configuration
   └─ Apply concepts to real-world scenarios
   └─ Design and implement networks
   └─ Production-grade deployment
```

---

## 🚀 Getting Started

### Quick Setup (All Projects)

```bash
# Clone the repository
git clone https://github.com/yourusername/Computer-Networks.git
cd Computer-Networks

# Create a workspace
mkdir -p workspace
cd workspace

# Follow individual project setup guides
cd ../Socket-Programing && make    # Socket Programming
cd ../Online && setup-cisco.sh     # Cisco Lab
cd ../Routing-Experiment && ns3    # Routing Simulation
cd ../Transport-Layer-Experiment && ns3  # TCP Analysis
```

### Individual Project Setup

#### Socket Programming
```bash
cd Socket-Programing
javac -d out src/Server/*.java src/Client/*.java
mkdir Public
java -cp out Server.HTTPServer        # Terminal 1
java -cp out Client.Client            # Terminal 2
```

#### Cisco Online Lab
```bash
# Download and open in Cisco Packet Tracer
cd Online
open 2005021.pkt  # or use File → Open in Packet Tracer
```

#### MANET Routing Experiment
```bash
cd Routing-Experiment/Code
./task1.sh       # Run protocol comparison
python3 analyze_csv.py  # Generate graphs
```

#### TCP Congestion Control
```bash
cd "Transport -Layer-Experiment/code"
./task1.sh       # Protocol comparison
./task2.sh       # State analysis
python3 plot.py  # Visualization
```

---

## Technology Stack

### Programming Languages
- **Java** - Socket programming and OOP concepts
- **C++** - NS-3 network simulation
- **Python** - Data analysis and visualization
- **Bash** - Automation and scripting

### Simulation & Tools
- **NS-3** - Network simulator for routing and transport layer analysis
- **Cisco Packet Tracer** - Network device simulation and configuration
- **Matplotlib** - Scientific visualization
- **Pandas** - Data manipulation and analysis

### Networking Technologies
- **TCP/IP** - Core internet protocol
- **IEEE 802.11** - WiFi standard
- **IEEE 802.1Q** - VLAN tagging
- **Cisco IOS** - Network device operating system

### Concepts & Algorithms
- **AODV/DSDV** - Routing protocols
- **TCP Variants** - Congestion control (NewReno, CUBIC, BBR, etc.)
- **Multi-threading** - Concurrent network programming
- **ACLs** - Security policies

---

## Installation Guide

### Prerequisites

```bash
# Update package manager
sudo apt update && sudo apt upgrade

# Install Java (for Socket Programming)
sudo apt install openjdk-11-jdk

# Install Python (for data analysis)
sudo apt install python3 python3-pip
pip install pandas matplotlib numpy scipy

# Install build tools
sudo apt install build-essential git
```

### NS-3 Installation (for Routing & Transport experiments)

```bash
# Download NS-3
cd ~
wget https://www.nsnam.org/release/ns-allinone-3.43.tar.bz2
tar xjf ns-allinone-3.43.tar.bz2
cd ns-allinone-3.43/ns-3.43

# Configure and build
./ns3 configure --enable-examples --enable-tests
./ns3 build
```

### Cisco Packet Tracer Installation

```bash
# Download from Cisco Networking Academy
# https://www.netacad.com/

# Linux installation
chmod +x PacketTracer8*.bin
./PacketTracer8*.bin

# Or install via package manager (Ubuntu)
sudo snap install packettracerlinux
```

---

## Project Highlights

### 🏆 Key Achievements

**Socket Programming**
- ✅ 500+ lines of production Java code
- ✅ Multi-threaded concurrent server handling 50+ clients
- ✅ Custom HTTP-like protocol with MIME type detection
- ✅ Robust error handling and file I/O operations

**Online Cisco Lab**
- ✅ Complete enterprise network configuration
- ✅ 4 VLANs with 192.168.x.x/24 subnets
- ✅ 3 Access Control Lists with granular security policies
- ✅ NAT implementation for ISP connectivity
- ✅ Full documentation of all commands and rationale

**MANET Routing**
- ✅ Comparison of 17 routing protocols
- ✅ 50+ node network simulations
- ✅ Comprehensive performance metrics collection
- ✅ Statistical analysis and visualization
- ✅ Publication-ready research findings

**TCP Congestion Control**
- ✅ Analysis of 17 TCP variants
- ✅ 7 detailed TCP state metrics traced
- ✅ Multi-flow fairness evaluation
- ✅ Automatic parameter assignment algorithm
- ✅ Professional visualization of TCP dynamics

**Network Configuration**
- ✅ Real-world Cisco IOS commands
- ✅ Practical implementation patterns
- ✅ Enterprise network design principles
- ✅ Complete cheatsheet documentation

---

## Key Concepts Covered

### Application Layer
- Socket programming (IPv4, TCP)
- Client-server architecture
- HTTP protocol fundamentals
- Multi-threaded server design
- MIME type handling
- File transfer protocols

### Transport Layer
- TCP protocol mechanics
- Congestion control algorithms
- Slow start and congestion avoidance
- Fast recovery mechanisms
- Round-trip time estimation
- Retransmission timeout calculation
- Fairness and efficiency metrics

### Network Layer
- IP routing and forwarding
- AODV protocol (reactive routing)
- DSDV protocol (proactive routing)
- Route discovery and maintenance
- Mobility impact on routing
- Path optimization strategies
- Performance metrics (throughput, delay, PDR)

### Data Link & Physical Layers
- VLAN configuration (IEEE 802.1Q)
- Switch port configuration
- Trunk and access links
- Access control lists
- Network switching concepts
- Physical topology design

### Practical Skills
- Cisco IOS command-line interface
- Network device configuration
- NS-3 network simulator usage
- Network traffic analysis
- Performance measurement
- Data visualization
- Scientific report writing

---

## Learning Outcomes

### After completing all projects, you will understand:

**Networking Fundamentals**
- How the internet actually works
- Communication between computers
- Protocol layering and encapsulation
- Network performance metrics

**Protocol Design**
- Congestion control strategies
- Routing algorithm fundamentals
- Fairness and efficiency trade-offs
- Protocol testing and comparison

**Practical Implementation**
- Socket programming for network applications
- Network device configuration
- Simulation-based network analysis
- Performance optimization techniques

**Research Methodology**
- Experimental design for networks
- Statistical analysis of results
- Reproducible research practices
- Academic paper preparation

**Professional Skills**
- Network architecture design
- Enterprise network configuration
- Troubleshooting network issues
- Documentation and communication

---

## 📊 Statistics

| Metric | Value |
|--------|-------|
| **Total Projects** | 5 |
| **Lines of Code** | 2000+ |
| **Programming Languages** | 3 (Java, C++, Python) |
| **TCP Variants Analyzed** | 17 |
| **Network Protocols Covered** | 15+ |
| **Documentation Pages** | 100+ |
| **Diagrams & Visualizations** | 50+ |
| **OSI Layers Covered** | 7/7 |

---

## Contributing

We welcome contributions! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Contribution Guidelines
- Follow existing code style
- Add documentation for new features
- Include comments for complex logic
- Test thoroughly before submitting
- Reference related issues

---

## 📖 Additional Resources

### Textbooks & References
- "Computer Networking" by Kurose & Ross
- "TCP/IP Illustrated" by Stevens & Fenner
- "The Art of Network Architecture" by Farrel & Drake
- RFC 793 (TCP), RFC 3561 (AODV), RFC 5681 (TCP CC)

### Online Learning
- [Cisco Networking Academy](https://www.netacad.com/)
- [NS-3 Official Documentation](https://www.nsnam.org/)
- [Wireshark Protocol Analysis](https://www.wireshark.org/)
- [Khan Academy - Networks](https://www.khanacademy.org/)

### Tools & Simulators
- NS-3 Network Simulator
- Cisco Packet Tracer
- Wireshark Protocol Analyzer
- GNS3 Network Emulator
- tcpdump Packet Sniffer

---

## 👨‍🎓 Author

**Student ID**: 2005021  
**Name**: Afzal  
**Institution**: Bangladesh University of Engineering and Technology (BUET)  
**Program**: Computer Science & Engineering  
**Level**: 3 | Term: II  
**Date**: 2026  

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

The course is part of the CSE321 and CSE322 curriculum at BUET. Respect intellectual property rights and cite appropriately.

---

## 📞 Contact & Support

For questions about the projects:
- 📧 Email: afzalhossan2005021@gmail.com
- 🔗 GitHub: https://github.com/AfzalHossan-2005021
- 💬 Discussions: Open an issue on GitHub

For course information:
- 📚 Course: CSE321 & CSE322 - Computer Networks
- 🏫 Institution: BUET Department of Computer Science & Engineering

---

<div align="center">

### 🎓 Master Computer Networks - From Theory to Practice! 🚀

This comprehensive collection demonstrates networking concepts through real implementation, simulation, and analysis. Start with the fundamentals and progress to advanced protocol optimization.

**[Start with Socket Programming →](Socket-Programing/README.md)**

---

**Last Updated**: May 26, 2026  
**Repository Status**: ✅ Complete  
**All Projects**: ✅ Documented & Ready to Use

</div>
