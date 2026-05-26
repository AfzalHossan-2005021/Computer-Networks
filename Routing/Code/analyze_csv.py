import os
import pandas as pd
import matplotlib.pyplot as plt

# Define directories
aodv_dir = "aodv_expt"
raodv_dir = "raodv_expt"

# Define filenames to load
files_to_load = [
    "manet-routing.node-analysis.csv",
    "manet-routing.packet-analysis.csv",
    "manet-routing.speed-analysis.csv",
]

# Load data from both directories
aodv_data = {}
raodv_data = {}

for file_name in files_to_load:
    aodv_path = os.path.join(aodv_dir, file_name)
    raodv_path = os.path.join(raodv_dir, file_name)
    
    aodv_data[file_name] = pd.read_csv(aodv_path)
    raodv_data[file_name] = pd.read_csv(raodv_path)

# Define a function to plot comparisons for a specific analysis
def plot_comparison(aodv_df, raodv_df, x_column, title, x_label):
    metrics = ["Network throughput", "End-to-end delay", "Packet delivery ratio", "Packet drop ratio"]
    colors = ["blue", "orange"]
    labels = ["AODV", "RAODV"]
    
    fig, axes = plt.subplots(2, 2, figsize=(12, 10))
    fig.suptitle(title, fontsize=16)
    
    for idx, metric in enumerate(metrics):
        ax = axes[idx // 2, idx % 2]
        ax.plot(aodv_df[x_column], aodv_df[metric], label=f"{labels[0]} - {metric}", color=colors[0], marker='o')
        ax.plot(raodv_df[x_column], raodv_df[metric], label=f"{labels[1]} - {metric}", color=colors[1], marker='s')
        ax.set_xlabel(x_label)
        ax.set_ylabel(metric)
        ax.legend()
        ax.grid(True)
    
    plt.tight_layout(rect=[0, 0, 1, 0.96])
    plt.show()

# Plot Node Analysis
plot_comparison(
    aodv_data["manet-routing.node-analysis.csv"],
    raodv_data["manet-routing.node-analysis.csv"],
    x_column="Number of nodes",
    title="Node Analysis",
    x_label="Number of Nodes",
)

# Plot Packet Analysis
plot_comparison(
    aodv_data["manet-routing.packet-analysis.csv"],
    raodv_data["manet-routing.packet-analysis.csv"],
    x_column="Number of packets per second",
    title="Packet Analysis",
    x_label="Number of Packets per Second",
)

# Plot Speed Analysis
plot_comparison(
    aodv_data["manet-routing.speed-analysis.csv"],
    raodv_data["manet-routing.speed-analysis.csv"],
    x_column="Speed of nodes",
    title="Speed Analysis",
    x_label="Speed of Nodes (m/s)",
)
