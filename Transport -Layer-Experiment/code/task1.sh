#!/bin/bash

# Set student ID
ID=2005021

# Array of transport_prot
transport_prot=("TcpNewReno" "TcpLinuxReno" "TcpHybla" "TcpHighSpeed" "TcpHtcp" "TcpVegas" "TcpScalable" "TcpVeno" "TcpBic" "TcpYeah" "TcpIllinois" "TcpWestwoodPlus" "TcpLedbat" "TcpLp" "TcpDctcp" "TcpCubic" "TcpBbr")


# Array of assigned transport_prot index
assigned_transport_prot_index[0]=$((($ID + 3) % ${#transport_prot[@]}))
assigned_transport_prot_index[1]=$((($ID ** 2 + 12) % ${#transport_prot[@]}))

if [ ${assigned_transport_prot_index[0]} = ${assigned_transport_prot_index[1]} ]; then
    assigned_transport_prot_index[1]=$(((${assigned_transport_prot_index[0]} + 7) % ${#transport_prot[@]}))
fi

# Get assigned transport_prot
assigned_transport_prot[0]=${transport_prot[${assigned_transport_prot_index[0]}]}
assigned_transport_prot[1]=${transport_prot[${assigned_transport_prot_index[1]}]}

# Print assigned transport_prot
echo "Transport protocol: ${assigned_transport_prot[@]}"

# Set num_fows
num_flows=$((($ID ** 3 % 7) + 1))

# Print num_flows
echo "Number of flows: $num_flows"

# Set bandwidth
bandwidth=$(($ID % 3 + 1))

# Print bandwidth
echo "Bandwidth: ${bandwidth}Mbps"

DIR="scratch"
FILE="tcp-variants-comparison.cc"
PREFIX="TcpVariantsComparison"

# check if the file exists
if [ ! -f "${DIR}/${FILE}" ]; then
    cp examples/tcp/${FILE} "${DIR}"
fi

# Run the application
for i in ${assigned_transport_prot_index[@]}; do
    mkdir -p "${DIR}/${transport_prot[$i]}"
    echo "Running ${transport_prot[$i]}..."
    ./ns3 run "${DIR}/${FILE} --transport_prot=${transport_prot[$i]} --bandwidth=${bandwidth}Mbps --num_flows=${num_flows} --prefix_name=${DIR}/${transport_prot[$i]}/${PREFIX} --tracing=true"
done

echo "Done data genetation!"

plot_file="plot.py"

# change the directory to scratch
cd "${DIR}"

# Change TcpIllinois to TcpEnewReno in scratch/plot.py
sed -i 's/task2_graphs/task1_graphs/g' "${plot_file}"

# Change TcpIllinois to TcpEnewReno in scratch/plot.py
sed -i 's/TcpEnewReno/TcpIllinois/g' "${plot_file}"

# Run the plot.py
echo "Running ${plot_file}..."
python3 "${plot_file}"

echo "Done plot generation!"