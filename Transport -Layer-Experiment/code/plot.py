import os
import pandas as pd
import matplotlib.pyplot as plt

# Define the protocol directories
protocols = ['TcpEnewReno', 'TcpNewReno']
flows = ['flow0', 'flow1']

# List of attributes to plot
attributes = ['cwnd', 'inflight', 'next-rx', 'next-tx', 'rto', 'rtt', 'ssth']

# Map the attribute to the column name
attribute_map = {
    'cwnd': 'Congestion Window Size (in bytes)',
    'inflight': 'Number of Bytes in Flight',
    'next-rx': 'Seq no. of the Next Expected Packet to be Received',
    'next-tx': 'Seq no. of the Next Packet to be Transmitted',
    'rto': 'Retransmission Timeout',
    'rtt': 'Round-Trip Time',
    'ssth': 'Slow Start Threshold (in bytes)',
}

# Create a directory for saved graphs
output_dir = "task2_graphs"
os.makedirs(output_dir, exist_ok=True)

# Iterate through attributes
for attribute in attributes:
    # Create the plot
    fig, ax = plt.subplots(figsize=(12, 8))

    # Find the maximum x-axis range
    max_x = 0

    # Store data frames for later processing
    data_frames = []

    for protocol in protocols:
        for flow in flows:
            for filename in os.listdir(protocol):
                if filename.endswith('.data') and attribute in filename and flow in filename:
                    file_path = os.path.join(protocol, filename)
                    df = pd.read_csv(file_path, header=None, sep=' ', names=['x', 'y'])
                    max_x = max(max_x, df['x'].max())  # Update the maximum x value
                    data_frames.append((df, protocol, flow))  # Store data frame with metadata

    # Normalize all data frames to the maximum x value
    for df, protocol, flow in data_frames:
        # Create a full x range from the data's last point to max_x
        last_value = df['y'].iloc[-1]  # Get the last y value
        filled_x = range(int(df['x'].iloc[-1]) + 1, int(max_x) + 1)  # Generate missing x values
        filled_y = [last_value] * len(filled_x)  # Use the last y value for missing entries

        # Append the new x and y values to the existing DataFrame
        filled_df = pd.concat([
            df,
            pd.DataFrame({'x': filled_x, 'y': filled_y})
        ])

        # Plot the data
        ax.plot(filled_df['x'], filled_df['y'], label=f'{flow} - {protocol}')

    # Set plot labels and title
    ax.set_xlabel('Time Stamp (s)')
    ax.set_ylabel(attribute_map[attribute])
    ax.set_title(f'{attribute_map[attribute]} vs Time')
    ax.legend()
    plt.grid()
    
    # Save the plot as a file
    output_file = os.path.join(output_dir, f'{attribute}.png')
    plt.savefig(output_file, format='png', dpi=300)
    plt.show()
