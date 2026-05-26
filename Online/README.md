# 🌐 Online Networking Lab - Cisco Configuration & Simulation

<div align="center">

![Cisco](https://img.shields.io/badge/Cisco-Networking-1f425f?style=for-the-badge&logo=cisco&logoColor=white)
![Packet Tracer](https://img.shields.io/badge/Packet%20Tracer-Simulator-brightgreen?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)
![Level](https://img.shields.io/badge/Level-Advanced-blue?style=for-the-badge)

A comprehensive Cisco networking lab demonstrating advanced network configuration including VLANs, Inter-VLAN routing, ACLs, and Network Address Translation (NAT).

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [Lab Objectives](#lab-objectives)
- [Network Topology](#network-topology)
- [Key Concepts](#key-concepts)
- [System Requirements](#system-requirements)
- [Installation & Setup](#installation--setup)
- [Configuration Guide](#configuration-guide)
- [Command Reference](#command-reference)
- [Network Architecture](#network-architecture)
- [Verification Commands](#verification-commands)
- [Troubleshooting](#troubleshooting)
- [Learning Outcomes](#learning-outcomes)
- [Author](#author)
- [License](#license)

---

## Overview

**Online Networking Lab** is an advanced Cisco network simulation project designed to provide hands-on experience with enterprise network configuration. This lab implements a multi-router, multi-switch network topology with sophisticated routing policies, virtual LANs, security controls, and network address translation.

Perfect for:
- Network administrators learning Cisco IOS
- Computer science students studying network design
- IT professionals preparing for Cisco certifications (CCNA)
- Network engineers designing enterprise networks

---

## 🎯 Lab Objectives

### Primary Objectives
1. **VLAN Segmentation** - Create and configure Virtual LANs for network isolation
2. **Inter-VLAN Routing** - Implement router-on-a-stick architecture for inter-VLAN communication
3. **Access Control** - Deploy Access Control Lists (ACLs) for traffic filtering and security
4. **Network Address Translation** - Configure NAT for private to public IP translation
5. **Static Routing** - Implement static routing between network segments
6. **Security Implementation** - Apply security policies to network devices

### Secondary Objectives
- Understand network segmentation principles
- Learn router and switch configuration
- Practice network troubleshooting
- Implement network security best practices

---

## 🏗️ Network Topology

```
                    ┌──────────────────────────────────┐
                    │     Internet/External Network    │
                    │         (223.50.11.0/28)         │
                    └────────────────┬─────────────────┘
                                     │ Serial 0/3/0
                                     │ IP: 223.50.11.1
                                     │
                          ┌──────────┴──────────┐
                          │        R3 (NAT)     │
                          │  192.168.50.1       │
                          │  Internal Gateway   │
                          └──────────┬──────────┘
                                     │ 35.0.0.0/30
                                     │
                          ┌──────────┴──────────┐
                          │        R1           │
                          │     Gateway         │
                          │  192.168.11.1       │
                          └──────────┬──────────┘
                                     │
                          ┌──────────┴──────────┐
                          │    S1 (Switch)      │
                          └┬────┬────┬────┬────┬┘
                           │    │    │    │    │
    VLAN 15       ┌────────┘    │    │    │    └────────┐
    Civil         │             │    │    │             │
   192.168.15.0   │   VLAN 25   │    │    │  VLAN 35    │  VLAN 11
   /24            │    MME      │    │    │    WRE      │  Mgmt
                  │  192.168.25 │    │    │  192.168.35 │  192.168.11
                  │     .0      │    │    │     .0      │    .0
                  │   /24       │    │    │   /24       │   /24
                  │             │    │    │             │
           ┌──────┴────┐  ┌─────┴────┴────┴─────┐  ┌────┴──────┐
           │  Devices  │  │   Trunk Link        │  │  Devices  │
           │  VLAN 15  │  │  (Native: VLAN 11)  │  │  VLAN 11  │
           └───────────┘  └─────────────────────┘  └───────────┘


                   ┌─────────────────────────────┐
                   │   Serial 0/0/0 (R1 to R2)   │
                   │   35.0.0.0/30               │
                   └──────────────┬──────────────┘
                                  │
                           ┌──────┴──────┐
                           │    R2       │
                           │  Gateway    │
                           │192.168.50.1 │ (Server Network)
                           └─────────────┘
```

---

## 💡 Key Concepts

### 1. **Virtual LANs (VLANs)**
VLANs logically segment networks without requiring separate physical connections.

```
VLAN Configuration:
├── VLAN 15: Civil Department     (192.168.15.0/24)
├── VLAN 25: MME Department       (192.168.25.0/24)
├── VLAN 35: WRE Department       (192.168.35.0/24)
└── VLAN 11: Management Network   (192.168.11.0/24)
```

**Benefits:**
- Network security and isolation
- Improved network performance
- Reduced broadcast domain size
- Simplified network management

### 2. **Router-on-a-Stick (Inter-VLAN Routing)**
A single router interface handles traffic for multiple VLANs using subinterfaces.

```
Configuration:
interface g0/0.15  → encapsulation dot1q 15
interface g0/0.25  → encapsulation dot1q 25
interface g0/0.35  → encapsulation dot1q 35
interface g0/0.11  → encapsulation dot1q 11
```

### 3. **Access Control Lists (ACLs)**
Security policies that control traffic flow between network segments.

```
ACL 115 (VLAN 15 - Civil):
├── DENY:   TCP to 192.168.50.5 port 80 (WWW)
├── PERMIT: TCP to 192.168.50.6 port 21 (FTP)
└── PERMIT: All other IP traffic

ACL 125 (VLAN 25 - MME):
├── PERMIT: TCP to 192.168.50.5 port 80 (WWW)
├── PERMIT: TCP to 192.168.50.6 port 21 (FTP)
└── PERMIT: All other IP traffic

ACL 135 (VLAN 35 - WRE):
├── PERMIT: TCP to 192.168.50.5 port 80 (WWW)
├── DENY:   TCP to 192.168.50.6 port 21 (FTP)
└── PERMIT: All other IP traffic
```

### 4. **Network Address Translation (NAT)**
Translates private IP addresses to public addresses for external communication.

```
NAT Configuration (R3):
├── Inside Pool: 223.50.11.16 - 223.50.11.17
├── Inside Networks:
│   ├── PERMIT: 192.168.80.0/24 (NAT enabled)
│   └── DENY:   192.168.70.0/24 (No NAT)
└── Translation Mode: Overload (PAT)
```

### 5. **Static Routing**
Explicit route configuration for known network paths.

```
Routing Policies:
R1: ip route 0.0.0.0 0.0.0.0 s0/0/0      (Default route to R2)
R2: ip route 0.0.0.0 0.0.0.0 s0/0/0      (Default route to R1)
R3: ip nat inside → interface g0/0/1      (NAT inside)
    ip nat outside → interface s0/3/0     (NAT outside)
```

---

## System Requirements

| Requirement | Specification |
|-------------|---------------|
| **Cisco Packet Tracer** | Version 8.0 or higher |
| **Operating System** | Windows, macOS, or Linux |
| **RAM** | Minimum 2 GB |
| **Disk Space** | 500 MB + file storage |
| **Display** | 1024x768 resolution minimum |
| **Network** | Simulated (no external network required) |

---

## Installation & Setup

### Step 1: Download and Install Cisco Packet Tracer
- Visit [Cisco Networking Academy](https://www.netacad.com/)
- Download and install Cisco Packet Tracer (Free with NetAcad account)
- Follow platform-specific installation instructions

### Step 2: Prepare Lab Files
```bash
# Extract/Copy lab files to working directory
cp 2005021.pkt ~/NetworkingLabs/
cp cheatsheet.txt ~/NetworkingLabs/
cp Online_commands.txt ~/NetworkingLabs/
```

### Step 3: Open Packet Tracer
```bash
# Linux/macOS
/opt/pt/bin/PacketTracer8

# Windows
C:\Program Files\Cisco Packet Tracer 8\bin\PacketTracer.exe
```

### Step 4: Load Lab File
1. Open Cisco Packet Tracer
2. File → Open → Select `2005021.pkt`
3. Wait for network topology to load (may take 30-60 seconds)
4. Verify all devices are visible

### Step 5: Verify Network
- Check device status indicators (green = connected)
- Verify cable connections
- Review device configurations

---

## Configuration Guide

### Basic Workflow for Configuration

#### Phase 1: Switch Configuration
```bash
# Enter Configuration Mode
Switch> enable
Switch# configure terminal

# Create VLANs
Switch(config)# vlan 15
Switch(config-vlan)# name Civil
Switch(config-vlan)# exit

# Assign Ports to VLANs
Switch(config)# interface fa0/5
Switch(config-if)# switchport mode access
Switch(config-if)# switchport access vlan 15
Switch(config-if)# no shutdown
Switch(config-if)# exit

# Configure Trunk
Switch(config)# interface fa0/1
Switch(config-if)# switchport mode trunk
Switch(config-if)# switchport trunk native vlan 11
Switch(config-if)# switchport trunk allowed vlan 15,25,35,11
Switch(config-if)# no shutdown
Switch(config-if)# end
```

#### Phase 2: Router Configuration
```bash
# Enter Configuration Mode
Router> enable
Router# configure terminal

# Create Subinterfaces for VLANs
Router(config)# interface g0/0.15
Router(config-subif)# encapsulation dot1q 15
Router(config-subif)# ip address 192.168.15.1 255.255.255.0
Router(config-subif)# exit

# Enable Physical Interface
Router(config)# interface g0/0
Router(config-if)# no shutdown
Router(config-if)# exit

# Configure Serial Interface
Router(config)# interface s0/0/0
Router(config-if)# clock rate 64000
Router(config-if)# ip address 35.0.0.1 255.255.255.252
Router(config-if)# no shutdown
Router(config-if)# exit

# Configure Routing
Router(config)# ip route 0.0.0.0 0.0.0.0 s0/0/0
Router(config)# exit
```

#### Phase 3: Security Configuration (ACLs)
```bash
Router# configure terminal

# Create Access Control List
Router(config)# access-list 115 deny tcp 192.168.15.0 0.0.0.255 host 192.168.50.5 eq www
Router(config)# access-list 115 permit tcp 192.168.15.0 0.0.0.255 host 192.168.50.6 eq ftp
Router(config)# access-list 115 permit ip any any

# Apply ACL to Interface
Router(config)# interface g0/0.15
Router(config-if)# ip access-group 115 in
Router(config-if)# exit

Router(config)# exit
```

#### Phase 4: NAT Configuration
```bash
Router# configure terminal

# Define NAT Pool
Router(config)# ip nat pool NAT-POOL 223.50.11.16 223.50.11.17 netmask 255.255.255.240

# Define Access List for Inside Networks
Router(config)# access-list 1 permit 192.168.80.0 0.0.0.255
Router(config)# access-list 1 deny 192.168.70.0 0.0.0.255

# Configure NAT
Router(config)# ip nat inside source list 1 pool NAT-POOL overload

# Mark Interfaces
Router(config)# interface g0/0
Router(config-if)# ip nat inside
Router(config-if)# exit

Router(config)# interface s0/3/0
Router(config-if)# ip nat outside
Router(config-if)# exit

Router(config)# exit
```

---

## 📚 Command Reference

### Device Access
| Command | Purpose |
|---------|---------|
| `enable` | Enter Privileged EXEC mode |
| `configure terminal` | Enter Configuration mode |
| `exit` | Exit current mode |
| `end` | Exit to Privileged EXEC (from config) |
| `quit` | Close session |

### Basic Configuration
| Command | Purpose |
|---------|---------|
| `hostname <name>` | Set device hostname |
| `enable secret <password>` | Set privileged password |
| `line console 0` → `password <pwd>` | Console password |
| `line vty 0 15` → `password <pwd>` | Remote access password |
| `service password-encryption` | Encrypt passwords |
| `banner motd #<message>#` | Display login message |

### VLAN Configuration
| Command | Purpose |
|---------|---------|
| `vlan <id>` | Create VLAN |
| `name <name>` | Name VLAN |
| `switchport mode access` | Set access port |
| `switchport access vlan <id>` | Assign to VLAN |
| `switchport mode trunk` | Enable trunking |
| `switchport trunk allowed vlan <id>` | Allow VLANs on trunk |
| `switchport trunk native vlan <id>` | Set native VLAN |

### Interface Configuration
| Command | Purpose |
|---------|---------|
| `interface <if>` | Select interface |
| `ip address <ip> <mask>` | Set IP address |
| `no shutdown` | Enable interface |
| `shutdown` | Disable interface |
| `encapsulation dot1q <id>` | Set VLAN encapsulation |

### Routing Commands
| Command | Purpose |
|---------|---------|
| `ip route <dest> <mask> <gateway>` | Add static route |
| `ip route 0.0.0.0 0.0.0.0 <gw>` | Default route |
| `clock rate <rate>` | Set serial clock (DCE) |

### ACL Commands
| Command | Purpose |
|---------|---------|
| `access-list <id> permit <rule>` | Allow traffic |
| `access-list <id> deny <rule>` | Block traffic |
| `ip access-group <id> in` | Apply inbound |
| `ip access-group <id> out` | Apply outbound |

### NAT Commands
| Command | Purpose |
|---------|---------|
| `ip nat pool <name> <start> <end> netmask <mask>` | Create NAT pool |
| `access-list <id> permit <network> <wildcard>` | Define inside networks |
| `ip nat inside source list <id> pool <name> overload` | Configure PAT |
| `ip nat inside` | Mark inside interface |
| `ip nat outside` | Mark outside interface |

### Verification Commands
| Command | Purpose |
|---------|---------|
| `show running-config` | Display active configuration |
| `show startup-config` | Display saved configuration |
| `show ip interface brief` | Interface status summary |
| `show vlan brief` | VLAN summary |
| `show interface <if> switchport` | Interface VLAN info |
| `show ip route` | Routing table |
| `show ip nat translations` | NAT translation table |
| `show run \| section access-list` | Display ACLs |

### File Operations
| Command | Purpose |
|---------|---------|
| `copy running-config startup-config` | Save configuration |
| `reload` | Restart device |
| `erase startup-config` | Reset to factory defaults |

---

## 🏢 Network Architecture

### Layer 3 (Network Layer) Organization

```
Organization Structure:
├── VLAN 15 (Civil Department)
│   ├── Network: 192.168.15.0/24
│   ├── Gateway: 192.168.15.1
│   ├── Host Range: 192.168.15.2 - 192.168.15.254
│   ├── Ports: S1 fa0/5
│   └── ACL: 115 (Restricted web access to 192.168.50.5)
│
├── VLAN 25 (MME Department)
│   ├── Network: 192.168.25.0/24
│   ├── Gateway: 192.168.25.1
│   ├── Host Range: 192.168.25.2 - 192.168.25.254
│   ├── Ports: S1 fa0/6
│   └── ACL: 125 (Full resource access)
│
├── VLAN 35 (WRE Department)
│   ├── Network: 192.168.35.0/24
│   ├── Gateway: 192.168.35.1
│   ├── Host Range: 192.168.35.2 - 192.168.35.254
│   ├── Ports: S1 fa0/7
│   └── ACL: 135 (Denied FTP access to 192.168.50.6)
│
├── VLAN 11 (Management)
│   ├── Network: 192.168.11.0/24
│   ├── Gateway: 192.168.11.1
│   ├── Host Range: 192.168.11.2 - 192.168.11.254
│   └── Ports: S1 fa0/1 (trunk)
│
├── Server Network
│   ├── Network: 192.168.50.0/24
│   ├── Gateway: 192.168.50.1 (R2)
│   ├── Web Server: 192.168.50.5
│   ├── FTP Server: 192.168.50.6
│   └── Connected to R2
│
└── External Network (Post-NAT)
    ├── Network: 223.50.11.0/28
    ├── NAT Pool: 223.50.11.16 - 223.50.11.17
    ├── Configured on R3
    └── Serial connection to ISP
```

---

## ✅ Verification Commands

### Verify VLAN Configuration
```bash
# Check VLAN database
show vlan brief

# Verify trunk configuration
show interface fa0/1 switchport

# Check interface VLAN assignment
show interface fa0/5 switchport
```

### Verify Routing
```bash
# Display routing table
show ip route

# Check specific route
show ip route 192.168.50.0

# Trace route to destination
tracert <destination>
```

### Verify ACLs
```bash
# Show all access lists
show access-lists

# Display specific ACL
show access-list 115

# Check ACL application on interface
show ip interface g0/0.15
```

### Verify NAT
```bash
# Display NAT configuration
show ip nat configuration

# Show NAT translations
show ip nat translations

# Display NAT statistics
show ip nat statistics
```

### Connectivity Tests
```bash
# Test reachability
ping <destination>

# Detailed path analysis
tracert <destination>

# Test specific protocol
telnet <host> <port>
```

---

## 🔧 Troubleshooting

### Issue: Devices Not Connecting
**Solution:**
1. Check cable connections in topology
2. Verify physical interface status: `show interface <if>`
3. Ensure interfaces are not shutdown: `no shutdown`
4. Check for IP address conflicts

### Issue: VLANs Not Working
**Solution:**
```bash
# Verify VLAN exists
show vlan brief

# Check trunk configuration
show interface <trunk-if> switchport

# Verify port VLAN assignment
show interface <access-if> switchport
```

### Issue: Routing Problems Between VLANs
**Solution:**
```bash
# Verify routing table
show ip route

# Check subinterface configuration
show running-config | section subinterface

# Verify subinterface is up
show ip interface brief | include g0/0
```

### Issue: ACL Not Blocking Traffic
**Solution:**
```bash
# Verify ACL syntax
show access-list 115

# Check ACL application
show ip interface g0/0.15 | include access list

# Test with specific traffic
ping <blocked-source> <blocked-destination>
```

### Issue: NAT Not Translating
**Solution:**
```bash
# Verify NAT pool
show ip nat pool NAT-POOL

# Check inside definition
show access-list 1

# Monitor translations
show ip nat translations verbose
```

### Issue: Serial Connection Down
**Solution:**
```bash
# Check serial interface status
show interface s0/0/0

# Verify IP addressing
show ip interface s0/0/0

# Check clock rate (DCE side)
show controllers s0/0/0
```

---

## 📖 Learning Outcomes

After completing this lab, you will understand:

### Networking Concepts
✅ VLAN segmentation and benefits  
✅ Inter-VLAN routing architectures  
✅ Network security with access control lists  
✅ Network address translation and overloading  
✅ Static routing configuration  
✅ Route selection and prioritization  

### Cisco IOS Skills
✅ Device configuration and management  
✅ Interface configuration procedures  
✅ Routing table interpretation  
✅ ACL creation and application  
✅ NAT pool configuration  
✅ Configuration backup and saving  

### Network Administration
✅ Network planning and design  
✅ Security policy implementation  
✅ Troubleshooting methodology  
✅ Performance monitoring  
✅ Documentation practices  

### Certification Preparation
This lab covers topics essential for:
- Cisco CCNA Routing & Switching
- Cisco CCENT (Entry level)
- CompTIA Network+ certification

---

## 🔗 File Descriptions

| File | Purpose | Size |
|------|---------|------|
| `2005021.pkt` | Cisco Packet Tracer simulation file with complete network topology | ~500KB |
| `cheatsheet.txt` | Comprehensive reference guide of Cisco IOS commands | 509 lines |
| `Online_commands.txt` | Configuration commands used in this specific lab | 157 lines |
| `README.md` | This documentation file | - |

---

## 👨‍🎓 Author

**By**: Afzal (Student ID: 2005021)  
**Institution**: [Computer Networks Course]  
**Project Type**: Networking Lab & Simulation  
**Date**: 2026  
**Level**: Advanced

---

## 📚 Additional Resources

### Cisco Documentation
- [Cisco IOS Command Reference](https://www.cisco.com/c/en/us/support/ios-nx-os-software/index.html)
- [Switchport Configuration Guide](https://www.cisco.com/c/en/us/td/docs/switches/lan/catalyst2950/software/release/12-1-4-ea1/configuration/guide/2950cfgch4.html)
- [Router Configuration Guide](https://www.cisco.com/c/en/us/td/docs/routers/access/2800/software/configuration/guide/icm_2800_cg.html)

### Learning Resources
- [Cisco Networking Academy](https://www.netacad.com/)
- [Cisco Learning Network](https://learningnetwork.cisco.com/)
- [YouTube: Cisco IOS Tutorials](https://www.youtube.com/results?search_query=cisco+ios+configuration)

### Certification Prep
- CCNA 200-301 Exam Topics
- Routing & Switching Fundamentals
- Network Security Basics
- Network Operations & Management

### Related Protocols & Technologies
- IEEE 802.1Q (VLAN Tagging)
- RFC 2544 (Network Performance)
- RFC 1631 (NAT - Network Address Translation)
- TCP/IP Protocol Suite

---

## 📋 Lab Checklist

- [ ] Cisco Packet Tracer installed
- [ ] Lab file (2005021.pkt) created/opened
- [ ] All devices visible in topology
- [ ] VLANs created (15, 25, 35, 11)
- [ ] Access ports assigned to VLANs
- [ ] Trunk port configured
- [ ] Subinterfaces created on router
- [ ] Static routes configured
- [ ] ACLs created and applied
- [ ] NAT pool configured
- [ ] Inside/outside interfaces marked
- [ ] All devices have running configurations saved
- [ ] Connectivity tests successful
- [ ] Inter-VLAN routing verified
- [ ] ACL blocking/allowing verified
- [ ] NAT translations visible

---

## License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

---

<div align="center">

### 🎓 Cisco Networking Lab for Education & Professional Development

**Master the fundamentals of enterprise networking! 🚀**

For questions or feedback, please contact your instructor or refer to the Cisco Networking Academy.

</div>
