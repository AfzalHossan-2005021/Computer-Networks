# 🌐 Transport Layer Experiment - TCP Congestion Control Analysis

<div align="center">

![NS-3](https://img.shields.io/badge/NS3-Network%20Simulator-blue?style=for-the-badge)
![TCP](https://img.shields.io/badge/TCP-Congestion%20Control-orange?style=for-the-badge)
![Networking](https://img.shields.io/badge/Transport%20Layer-Simulation-brightgreen?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)
![Academic](https://img.shields.io/badge/Academic-Research-purple?style=for-the-badge)

A comprehensive network simulation project analyzing TCP congestion control mechanisms and comparing multiple TCP variant implementations using the NS-3 network simulator.

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [Project Objectives](#project-objectives)
- [Key Features](#key-features)
- [System Architecture](#system-architecture)
- [System Requirements](#system-requirements)
- [Installation & Setup](#installation--setup)
- [TCP Variants](#tcp-variants)
- [Usage Guide](#usage-guide)
- [Experiment Configuration](#experiment-configuration)
- [Performance Metrics](#performance-metrics)
- [Project Structure](#project-structure)
- [Running Experiments](#running-experiments)
- [Data Analysis & Visualization](#data-analysis--visualization)
- [Results Interpretation](#results-interpretation)
- [Customization Guide](#customization-guide)
- [Troubleshooting](#troubleshooting)
- [Learning Outcomes](#learning-outcomes)
- [Author](#author)
- [License](#license)

---

## Overview

**Transport Layer Experiment** is an advanced network simulation project that investigates TCP (Transmission Control Protocol) congestion control mechanisms. The project uses the NS-3 network simulator to compare multiple TCP variant implementations and analyze their performance under various network conditions.

This comprehensive study evaluates how different TCP algorithms respond to network congestion, adapt their transmission rates, and optimize throughput while maintaining fairness and responsiveness. The project implements detailed tracing of TCP behaviors and generates visual comparisons across 17 different TCP variants.

Perfect for:
- Network researchers studying TCP performance
- Computer science students learning transport layer protocols
- Network engineers evaluating congestion control strategies
- Academics preparing papers on TCP optimization
- IT professionals interested in network performance tuning

---

## 🎯 Project Objectives

### Primary Objectives
1. **Protocol Comparison** - Analyze and compare 17 different TCP congestion control algorithms
2. **Behavior Analysis** - Examine TCP congestion window dynamics and adaptation mechanisms
3. **Network Condition Testing** - Evaluate protocol performance under varying bandwidth and flow conditions
4. **Performance Metrics** - Collect comprehensive TCP state variables and performance indicators
5. **Visual Analysis** - Generate publication-quality graphs of TCP behavior
6. **Custom Implementation** - Implement and evaluate enhanced TCP algorithms

### Secondary Objectives
- Understand TCP state machine and congestion control phases
- Master NS-3 TCP tracing and analysis capabilities
- Develop data visualization and statistical analysis skills
- Create reproducible experimental methodology
- Document findings for academic publication
- Optimize TCP algorithms for specific network scenarios

---

## ✨ Key Features

### Simulation Capabilities
- **Multi-Protocol Support** - 17 different TCP congestion control algorithms
- **Flexible Topology** - Point-to-point and more complex network configurations
- **Realistic Conditions** - Variable bandwidth, latency, and packet loss
- **Multi-Flow Scenarios** - Support for multiple concurrent TCP flows
- **Detailed Tracing** - Per-packet and per-connection state tracking
- **Congestion Simulation** - Simulate bottleneck links and queue management

### TCP Variants Supported
- **Classic Algorithms**: NewReno, Tahoe, Reno, Vegas
- **High-Speed Networks**: HighSpeed, Scalable, Fast, Illinois
- **Hybrid Approaches**: Westwood+, Veno, Hybla, Yeah
- **Modern Implementations**: CUBIC, BBR, DCTCP
- **Linux Variants**: Linux Reno
- **Special Purpose**: Ledbat, LP (Low Priority)

### Data Collection
- **Congestion Window (CWND)** - Sending window size evolution
- **Slow Start Threshold (SSTH)** - Transition point between phases
- **Round-Trip Time (RTT)** - Latency measurement and adaptation
- **Retransmission Timeout (RTO)** - Timeout calculation
- **In-Flight Bytes** - Number of unacknowledged packets
- **Sequence Numbers** - Next TX and RX sequence tracking
- **Flow-Level Statistics** - Throughput, delay, packet loss

### Analysis Tools
- **Python Analytics** - Automated data processing
- **Matplotlib Visualization** - High-quality performance graphs
- **Multi-Flow Comparison** - Side-by-side protocol analysis
- **Time-Series Analysis** - TCP behavior over simulation time
- **Statistical Reporting** - Comprehensive metric summaries

---

## 🏗️ System Architecture

### Simulation Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    NS-3 Simulator Core                      │
│                   (Event-Driven Engine)                     │
└───────────────────────────┬─────────────────────────────────┘
                            │
        ┌───────────────────┼───────────────────┐
        │                   │                   │
        ▼                   ▼                   ▼
┌──────────────┐    ┌──────────────┐    ┌──────────────┐
│ TCP Stack    │    │  IP/Routing  │    │ Point-to-    │
│              │    │  Layer       │    │ Point Link   │
└──────────────┘    └──────────────┘    └──────────────┘
        │                   │                   │
        └───────────────────┼───────────────────┘
                            │
        ┌───────────────────┴───────────────────┐
        │                                       │
        ▼                                       ▼
┌───────────────────────┐          ┌──────────────────────┐
│  TCP Congestion Ctrl  │          │  Applications Layer  │
│     (17 Variants)     │          │  (BulkSendApp)       │
├───────────────────────┤          └──────────────────────┘
│ • Tahoe/Reno          │
│ • NewReno             │          ┌──────────────────────┐
│ • Vegas               │          │  Traffic Monitoring  │
│ • HighSpeed           │          │  • Flow Monitor      │
│ • CUBIC               │          │  • Statistics Helper │
│ • BBR                 │          └──────────────────────┘
│ • Westwood+           │                       │
│ • And 10 more...      │                       │
└───────────────────────┘                       │
        │                                       │
        └────────────────────┬──────────────────┘
                             │
              ┌──────────────┴──────────────┐
              │                             │
              ▼                             ▼
   ┌────────────────────┐       ┌──────────────────┐
   │ TCP State Tracing  │       │ Flow Statistics  │
   │ • CWND trace       │       │ • Throughput     │
   │ • SSTH trace       │       │ • Delay          │
   │ • RTT trace        │       │ • Loss ratio     │
   │ • RTO trace        │       │ • Goodput        │
   └────────────────────┘       └──────────────────┘
              │                             │
              └──────────────┬──────────────┘
                             │
              ┌──────────────┴──────────────┐
              │                             │
              ▼                             ▼
   ┌────────────────────┐       ┌──────────────────┐
   │ Data Files (.data) │       │ Flow XML/CSV     │
   │ • cwnd.data        │       │ Results          │
   │ • ssth.data        │       │ • Flow throughput│
   │ • rtt.data         │       │ • Packet stats   │
   │ • inflight.data    │       │ • Delay stats    │
   └────────────────────┘       └──────────────────┘
              │                             │
              └──────────────┬──────────────┘
                             │
                   ┌─────────┴─────────┐
                   │                   │
                   ▼                   ▼
           ┌─────────────────┐  ┌──────────────┐
           │  Python Plots   │  │ Analysis     │
           │ (plot.py)       │  │ Reports      │
           └─────────────────┘  └──────────────┘
```

### Experiment Workflow

```
Task 1: Protocol Comparison
┌──────────────────────────────────────────────────┐
│ 1. Determine assigned TCP variants (by ID)       │
│ 2. Set network parameters (bandwidth, flows)     │
│ 3. Run simulation for each protocol              │
│ 4. Collect aggregate statistics                  │
│ 5. Compare performance metrics                   │
└──────────────────────────────────────────────────┘

Task 2: Detailed State Analysis
┌──────────────────────────────────────────────────┐
│ 1. Run simulation with tracing enabled           │
│ 2. Generate TCP state data traces                │
│ 3. Extract per-flow metrics                      │
│ 4. Plot TCP state evolution over time            │
│ 5. Compare protocol behavior                     │
└──────────────────────────────────────────────────┘

Output
┌──────────────────────────────────────────────────┐
│ • Comparison graphs (task1_graphs/)              │
│ • Time-series traces (task2_graphs/)             │
│ • Data files (.data, XML, CSV)                   │
│ • Analysis reports                               │
└──────────────────────────────────────────────────┘
```

---

## System Requirements

### Software Requirements

| Component | Specification |
|-----------|---------------|
| **Operating System** | Linux (Ubuntu 20.04+, Debian 11+) or macOS |
| **NS-3 Version** | 3.37 or higher |
| **Python Version** | 3.8 or higher |
| **C++ Compiler** | GCC 7.0+ or Clang 5.0+ |
| **Build System** | WAF |

### Hardware Requirements

| Resource | Minimum | Recommended |
|----------|---------|-------------|
| **CPU** | Dual-core 2.0 GHz | Quad-core 2.5+ GHz |
| **RAM** | 2 GB | 8 GB |
| **Storage** | 10 GB | 30 GB |
| **Disk I/O** | Standard HDD | SSD for faster runs |

### Python Libraries

```
pandas              # Data manipulation and analysis
matplotlib          # Plotting and visualization
numpy               # Numerical computing
scipy               # Scientific computing (optional)
```

---

## Installation & Setup

### Step 1: Install NS-3

```bash
# Download NS-3 (version 3.37 or higher)
cd ~
wget https://www.nsnam.org/release/ns-allinone-3.43.tar.bz2
tar xjf ns-allinone-3.43.tar.bz2
cd ns-allinone-3.43/ns-3.43

# Configure with examples
./ns3 configure --enable-examples --enable-tests

# Build
./ns3 build
```

### Step 2: Install Python Dependencies

```bash
# Install required packages
pip install pandas matplotlib numpy

# Verify installation
python3 -c "import pandas; import matplotlib; print('✓ Dependencies OK')"
```

### Step 3: Setup TCP Congestion Operations

```bash
# Copy TCP congestion operations files
cp Transport\ -Layer-Experiment/code/tcp-congestion-ops.h \
   ~/ns-allinone-3.43/ns-3.43/src/internet/model/

cp Transport\ -Layer-Experiment/code/tcp-congestion-ops.cc \
   ~/ns-allinone-3.43/ns-3.43/src/internet/model/

# Rebuild NS-3
cd ~/ns-allinone-3.43/ns-3.43
./ns3 build
```

### Step 4: Setup Simulation Scripts

```bash
# Copy simulation script
cp Transport\ -Layer-Experiment/code/tcp-variants-comparison.cc \
   ~/ns-allinone-3.43/ns-3.43/scratch/

# Copy analysis script
cp Transport\ -Layer-Experiment/code/plot.py \
   ~/ns-allinone-3.43/ns-3.43/scratch/

# Copy experiment scripts
cp Transport\ -Layer-Experiment/code/task*.sh \
   ~/ns-allinone-3.43/ns-3.43/

# Make scripts executable
chmod +x ~/ns-allinone-3.43/ns-3.43/task*.sh
```

### Step 5: Create Output Directories

```bash
cd ~/ns-allinone-3.43/ns-3.43/scratch

# Create result directories
mkdir -p task1_graphs
mkdir -p task2_graphs
mkdir -p results

echo "✓ Setup complete!"
```

---

## 🛣️ TCP Variants

### Legacy Algorithms

| Algorithm | Year | Characteristics |
|-----------|------|-----------------|
| **Tahoe** | 1988 | Slow start, AIMD, resets CWND on loss |
| **Reno** | 1990 | Fast recovery, partial CWND reset on loss |
| **NewReno** | 1996 | Better fast recovery, improved loss detection |
| **Vegas** | 1994 | Delay-based, uses RTT instead of loss |
| **Westwood+** | 2002 | Estimates bandwidth, reduces false congestion |

### High-Speed Algorithms (for high-bandwidth networks)

| Algorithm | Characteristics |
|-----------|-----------------|
| **HighSpeed** | Aggressive CWND increase for high-BDP networks |
| **Scalable** | Scales well with network capacity |
| **Fast** | Optimized for high-speed WAN links |
| **Illinois** | Adaptive rate control for large networks |

### Hybrid Approaches

| Algorithm | Description |
|-----------|-------------|
| **Westwood+** | Estimates minimum RTT for bandwidth calculation |
| **Veno** | Vegas with loss-based recovery |
| **Hybla** | Adapts to RTT for fairness |
| **Yeah** | Combines Vegas and NewReno strategies |

### Modern Implementations

| Algorithm | Focus |
|-----------|-------|
| **CUBIC** | Default in Linux, optimized for modern networks |
| **BBR** | Congestion-based, models as pipe delivery rate |
| **DCTCP** | Data center optimized, explicit congestion notification |

### Special Purpose

| Algorithm | Purpose |
|-----------|---------|
| **Ledbat** | Low-priority data transfer, yield-friendly |
| **LP** | Lower throughput than other flows, delay-optimized |

---

## Usage Guide

### Task 1: Protocol Comparison

Run automated comparison of assigned TCP variants:

```bash
cd ~/ns-allinone-3.43/ns-3.43

# Execute task 1 script
./task1.sh

# Expected output:
# Transport protocol: TcpWestwoodPlus TcpBbr
# Number of flows: 2
# Bandwidth: 2Mbps
# Running TcpWestwoodPlus...
# Running TcpBbr...
```

**What It Does:**
1. Determines your assigned TCP variants based on student ID
2. Sets bandwidth and number of flows automatically
3. Runs simulation for each protocol
4. Generates statistics files
5. Compares performance metrics

### Task 2: Detailed State Analysis

Analyze TCP congestion window dynamics:

```bash
cd ~/ns-allinone-3.43/ns-3.43

# Execute task 2 script
./task2.sh

# Expected output:
# Transport protocol: TcpWestwoodPlus TcpBbr
# Number of flows: 2
# Bandwidth: 2Mbps
# Running TcpEnewReno...
# Running TcpWestwoodPlus...
```

**What It Does:**
1. Runs simulation with detailed packet-level tracing
2. Generates TCP state files (.data):
   - Congestion window (CWND)
   - Slow start threshold (SSTH)
   - Round-trip time (RTT)
   - Retransmission timeout (RTO)
   - In-flight bytes
   - Next TX/RX sequence numbers
3. Exports data for analysis

### Generate Visualizations

```bash
cd ~/ns-allinone-3.43/ns-3.43/scratch

# Run Python analysis
python3 plot.py

# This generates graphs for all TCP state variables:
# - Congestion Window Size vs Time
# - Slow Start Threshold vs Time
# - Round-Trip Time vs Time
# - Retransmission Timeout vs Time
# - In-Flight Bytes vs Time
# - Sequence Numbers vs Time
```

---

## Experiment Configuration

### Student ID-Based Assignment

Network parameters are automatically assigned based on student ID:

```bash
Student ID: 2005021

TCP Variant 1: index = (2005021 + 3) % 17 = 7 → TcpVeno
TCP Variant 2: index = (2005021^2 + 12) % 17 = 13 → TcpLp

Number of flows: (2005021^3) % 7 + 1 = 3 flows
Bandwidth: (2005021 % 3) + 1 = 2 Mbps
```

### Customizing Parameters

Edit `task1.sh` to modify:

```bash
# Change TCP variants
transport_prot=("TcpNewReno" "TcpCubic" "TcpBbr")

# Modify bandwidth
bandwidth=5  # Mbps

# Change number of flows
num_flows=5

# Adjust simulation parameters in tcp-variants-comparison.cc
# - DataRate
# - Delay
# - MaxPackets
# - RxBufferSize
# - TxBufferSize
```

---

## 📊 Performance Metrics

### Congestion Window (CWND)
- **Definition**: Size of the sending window in bytes
- **Significance**: Indicates protocol's bandwidth utilization strategy
- **Interpretation**: Larger CWND = higher throughput potential

### Slow Start Threshold (SSTH)
- **Definition**: Boundary between slow start and congestion avoidance
- **Significance**: Determines protocol responsiveness and prudence
- **Interpretation**: Lower SSTH = more conservative, higher = more aggressive

### Round-Trip Time (RTT)
- **Definition**: Time for packet round trip (send + receive)
- **Unit**: Milliseconds
- **Significance**: Key parameter for timeout calculation and flow control

### Retransmission Timeout (RTO)
- **Definition**: Deadline for packet retransmission
- **Unit**: Milliseconds
- **Formula**: RTO = SRTT + 4 × RTTVAR (typically)
- **Significance**: Too aggressive → unnecessary retransmissions; too conservative → waste of time

### In-Flight Bytes
- **Definition**: Number of unacknowledged transmitted bytes
- **Significance**: Indicates pipeline utilization
- **Target**: Should match BDP (Bandwidth Delay Product)

### Throughput
- **Definition**: Data successfully delivered per unit time
- **Unit**: Mbps or Kbps
- **Formula**: Total bytes delivered / simulation time

### Packet Delivery Ratio (PDR)
- **Definition**: Percentage of packets reaching destination
- **Unit**: Percentage (%)
- **Formula**: (Packets received / Packets sent) × 100

### Average Delay
- **Definition**: Mean time from send to receive
- **Unit**: Milliseconds
- **Formula**: Sum of all delays / Number of packets

---

## 📁 Project Structure

```
Transport-Layer-Experiment/
├── README.md                          # This file
├── 2005021_report.pdf                # Project report and findings
├── ns3_assignment2.pdf               # Assignment specification
│
└── code/
    ├── README.txt                     # Setup instructions
    │
    ├── tcp-variants-comparison.cc    # Main simulation program
    │   ├── RoutingExperiment class   # Simulation framework
    │   ├── TCP variant selection     # Protocol switching
    │   ├── Topology setup            # P2P link configuration
    │   ├── Traffic generation        # BulkSendApplication
    │   ├── State tracing             # CWND, RTT, RTO tracking
    │   ├── Callback handlers         # Event handlers
    │   └── CSV output               # Results export
    │
    ├── tcp-congestion-ops.h          # Header for TCP ops
    │   ├── TCP state definitions
    │   ├── Callback interfaces
    │   └── Helper functions
    │
    ├── tcp-congestion-ops.cc         # Implementation
    │   ├── Tracer functions
    │   ├── Data formatting
    │   └── File I/O operations
    │
    ├── plot.py                       # Analysis and visualization
    │   ├── Load .data files
    │   ├── Process time-series data
    │   ├── Generate comparison plots
    │   └── Handle multiple flows
    │
    ├── task1.sh                      # Protocol comparison experiment
    │   ├── Parse student ID
    │   ├── Determine assigned protocols
    │   ├── Run simulations
    │   └── Collect statistics
    │
    └── task2.sh                      # Detailed state analysis experiment
        ├── Enable state tracing
        ├── Run simulations
        ├── Generate trace files
        └── Export flow statistics
```

---

## 🚀 Running Experiments

### Full Automated Workflow

```bash
cd ~/ns-allinone-3.43/ns-3.43

# Run complete Task 1 and Task 2
echo "=== Running Task 1: Protocol Comparison ==="
./task1.sh

echo "=== Running Task 2: State Analysis ==="
./task2.sh

echo "=== Generating Visualizations ==="
cd scratch
python3 plot.py

echo "=== All experiments complete! ==="
```

### Single Protocol Run

```bash
# Test specific TCP variant
./ns3 run "tcp-variants-comparison \
    --transport_prot=TcpCubic \
    --bandwidth=5Mbps \
    --num_flows=3 \
    --prefix_name=scratch/test/result \
    --tracing=true"
```

### Batch Testing

```bash
# Create custom batch test script
cat > custom_test.sh << 'EOF'
#!/bin/bash
for protocol in TcpNewReno TcpCubic TcpBbr; do
    for bandwidth in 1 2 5 10; do
        echo "Testing $protocol at ${bandwidth}Mbps..."
        ./ns3 run "tcp-variants-comparison \
            --transport_prot=$protocol \
            --bandwidth=${bandwidth}Mbps \
            --num_flows=2 \
            --tracing=true"
    done
done
EOF

chmod +x custom_test.sh
./custom_test.sh
```

---

## 📈 Data Analysis & Visualization

### Understanding Plot.py Output

The `plot.py` script generates 7 comparison graphs:

```
1. Congestion Window Size (CWND) vs Time
   ├─ Shows sending rate adaptation
   ├─ X-axis: Simulation time (seconds)
   └─ Y-axis: CWND (bytes)

2. Slow Start Threshold (SSTH) vs Time
   ├─ Shows congestion detection events
   ├─ X-axis: Simulation time (seconds)
   └─ Y-axis: SSTH (bytes)

3. Round-Trip Time (RTT) vs Time
   ├─ Shows network latency changes
   ├─ X-axis: Simulation time (seconds)
   └─ Y-axis: RTT (milliseconds)

4. Retransmission Timeout (RTO) vs Time
   ├─ Shows timeout calculation dynamics
   ├─ X-axis: Simulation time (seconds)
   └─ Y-axis: RTO (milliseconds)

5. In-Flight Bytes vs Time
   ├─ Shows unacknowledged data
   ├─ X-axis: Simulation time (seconds)
   └─ Y-axis: Bytes in flight

6. Next TX Sequence vs Time
   ├─ Shows transmission progress
   └─ Increasing stairstep pattern = network loss

7. Next RX Sequence vs Time
   ├─ Shows reception progress
   └─ Flat regions = waiting for data
```

### Interpreting CWND Behavior

```
Linear Increase (Congestion Avoidance):
/
Protocol is in safe operation, increasing rate slowly

Sharp Drop:
\
Congestion detected, backing off transmission

Quick Recovery:
/
Recovering from congestion, resuming transmission

Plateau:
___
Reached network capacity limit
```

---

## 📋 Results Interpretation

### Comparing TCP Variants

**High-Speed vs Classic:**
- High-speed (CUBIC, BBR) = higher initial CWND
- Classic (NewReno, Reno) = more conservative growth

**Delay-Based vs Loss-Based:**
- Delay-based (Vegas) = lower average CWND
- Loss-based (NewReno, CUBIC) = higher CWND before loss

**Fairness Analysis:**
- Equal CWND curves = fair protocol
- Diverging curves = unfair protocol

### Performance Metrics Table

```
Protocol        | Throughput | Fairness | Responsiveness | Stability
────────────────┼────────────┼──────────┼────────────────┼───────────
NewReno         | Medium     | Good     | Medium         | Good
CUBIC           | High       | Fair     | Good           | Very Good
BBR             | Very High  | Fair     | Very Good      | Good
Vegas           | Low-Med    | Good     | Very Good      | Good
Westwood+       | High       | Good     | Good           | Good
HighSpeed       | Very High  | Fair     | Good           | Medium
```

---

## ⚙️ Customization Guide

### Modifying Topology

```cpp
// tcp-variants-comparison.cc

// Change link bandwidth
PointToPointHelper p2p;
p2p.SetDeviceAttribute("DataRate", StringValue("10Mbps"));

// Change link delay
p2p.SetChannelAttribute("Delay", StringValue("10ms"));

// Change queue size
PointToPointHelper p2p;
p2p.SetQueue("ns3::DropTailQueue<Packet>",
             "MaxSize", StringValue("100p"));
```

### Modifying Traffic Pattern

```cpp
// Change number of flows
Config::SetDefault("ns3::BulkSendApplication::SendSize", 
                   UintegerValue(512));

// Change packet size
BulkSendHelper tcpBulkSend("ns3::TcpSocketFactory", 
                           InetSocketAddress(serverIp, port));

// Change application duration
bulkSendApps.Stop(Seconds(simulationTime));
```

### Adding Custom Metrics

```cpp
// Add new trace connection
socket->TraceConnectWithoutContext("CongestionWindow",
                                  MakeCallback(&YourTraceCallback));

// Custom callback implementation
void YourTraceCallback(uint32_t oldCwnd, uint32_t newCwnd)
{
    // Process new metric
}
```

---

## 🔧 Troubleshooting

### Issue: NS-3 Build Fails

**Solution:**
```bash
# Clean and rebuild
./ns3 clean
./ns3 configure --enable-examples
./ns3 build

# Check dependencies
apt-get install build-essential python3-dev gcc g++ pkg-config
```

### Issue: "tcpvariants-comparison not found"

**Solution:**
```bash
# Verify file location
ls scratch/tcp-variants-comparison.cc

# Copy if missing
cp examples/tcp/tcp-variants-comparison.cc scratch/

# Rebuild
./ns3 build
```

### Issue: "ModuleNotFoundError: No module named 'pandas'"

**Solution:**
```bash
# Install required packages
pip install pandas matplotlib numpy

# Verify
python3 -c "import pandas; print(pandas.__version__)"
```

### Issue: Simulation Produces No Output Files

**Solution:**
```bash
# Verify output directory exists
mkdir -p scratch/TcpNewReno
mkdir -p scratch/results

# Check file permissions
chmod -R 755 scratch/

# Run with verbose mode
./ns3 run "tcp-variants-comparison" --LogComponentEnable="* --LogLevel=LOG_DEBUG"
```

### Issue: Plot.py Fails to Find Data Files

**Solution:**
```bash
# Verify data files are generated
find . -name "*.data" -o -name "*.csv"

# Check file format
head -n5 TcpNewReno/*.data

# Update plot.py paths if needed
protocols = ['./TcpNewReno', './TcpCubic']  # Correct paths
```

### Issue: Memory Error During Long Simulations

**Solution:**
```bash
# Reduce simulation parameters
--num_flows=1  # Fewer concurrent connections
--duration=50  # Shorter simulation time

# Monitor memory usage
top -p $(pgrep ns-3)
```

### Issue: Timeout or Slow Execution

**Solution:**
```bash
# Enable optimized build
./ns3 configure --build-profile=optimized
./ns3 build

# Reduce network size or simulation time
# Use SSD for faster I/O operations
# Run on multi-core CPU with low system load
```

---

## 📚 Learning Outcomes

### TCP Protocol Understanding
✅ TCP congestion window mechanism and adaptation  
✅ Slow start, congestion avoidance, and fast recovery  
✅ Round-trip time measurement and RTO calculation  
✅ Retransmission strategies and timeout handling  
✅ Flow control vs congestion control distinction  

### Network Simulation Skills
✅ NS-3 simulator architecture and event handling  
✅ Network topology construction and configuration  
✅ Application layer traffic generation  
✅ Packet-level tracing and statistics collection  
✅ Data export and external analysis  

### Performance Analysis
✅ TCP metrics interpretation and significance  
✅ Protocol comparison methodology  
✅ Identifying performance bottlenecks  
✅ Fairness and efficiency trade-offs  
✅ Protocol selection for specific scenarios  

### Research & Documentation
✅ Experimental design for networking research  
✅ Reproducible simulation methodology  
✅ Data visualization and presentation  
✅ Scientific report writing  
✅ Publication-ready result generation  

### Certification Preparation
This project covers topics relevant to:
- CCNA TCP/IP fundamentals
- Network+ transport layer concepts
- CCNP routing and optimization
- Advanced network engineering

---

## 📖 File Descriptions

| File | Purpose | Type |
|------|---------|------|
| `tcp-variants-comparison.cc` | Main simulation engine | C++ |
| `tcp-congestion-ops.h/cc` | TCP state tracers | C++ |
| `plot.py` | Data visualization | Python |
| `task1.sh` | Protocol comparison experiment | Bash |
| `task2.sh` | State analysis experiment | Bash |
| `2005021_report.pdf` | Project findings and analysis | Report |

---

## 👨‍🎓 Author

**By**: Afzal (Student ID: 2005021)  
**Institution**: [Computer Networks Course]  
**Project Type**: Transport Layer Simulation & Analysis  
**Date**: 2026  
**Level**: Advanced Graduate-level  

---

## 🔗 References & Resources

### TCP/IP Standards
- RFC 793 - TCP Protocol Specification
- RFC 2581 - TCP Congestion Control
- RFC 3168 - ECN (Explicit Congestion Notification)
- RFC 5681 - TCP Congestion Control (CUBIC, NewReno)

### Research Papers
- "CUBIC: A New TCP-friendly High-speed TCP Variant" (2008)
- "BBR: Congestion-Based Congestion Control" (2016)
- "TCP Westwood Performance in Heterogeneous Networks" (2002)
- "A Study of TCP Variants for High Performance" (2005)

### NS-3 Documentation
- [NS-3 Tutorial](https://www.nsnam.org/docs/tutorial/)
- [NS-3 Models Reference](https://www.nsnam.org/docs/models/)
- [NS-3 Examples](https://gitlab.com/nsnam/ns-3-dev/-/tree/master/examples/tcp/)

### TCP Learning Resources
- "Computer Networking" by Kurose & Ross
- "TCP/IP Illustrated" by Stevens
- [Wireshark TCP Analysis](https://www.wireshark.org/)
- [TCP Algorithm Visualizations](https://www.youtube.com/watch?v=VKr0RsYjnpc)

---

## 📋 Experiment Checklist

- [ ] NS-3 version 3.37+ installed
- [ ] All Python dependencies installed
- [ ] TCP variant files copied
- [ ] Simulation scripts in scratch/ directory
- [ ] Output directories created
- [ ] Task1.sh executed successfully
- [ ] Task2.sh executed successfully
- [ ] Data files (.data, XML, CSV) generated
- [ ] plot.py runs without errors
- [ ] Comparison graphs generated
- [ ] Results analyzed and interpreted
- [ ] Report prepared with findings
- [ ] Performance metrics documented

---

## License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

---

<div align="center">

### 🚀 Master TCP Congestion Control Through Simulation!

**Understand how the internet regulates data flow - from fundamentals to advanced algorithms.**

This project provides hands-on experience with real TCP implementations and modern congestion control innovations.

</div>
