import csv
import re

in_file = "java_100_results.txt"
out_file = in_file[:-4] + ".csv"

with open(in_file, "r") as f:
    data = f.readlines()
f.close()

data = ''.join(data)

# Split data into sections based on "BEGIN" and "END" markers
sections = re.split(r'BEGIN - |END - ', data)
sections = [section.strip() for section in sections if section.strip()]
sections = sections[1:]
# Create and open a CSV file for writing
csv_file_path = out_file
with open(csv_file_path, mode='w', newline='') as csv_file:
    fieldnames = ['Algorithm', 'Run', 'Search Value', 'Real Time', 'User Time', 'Sys Time']
    writer = csv.DictWriter(csv_file, fieldnames=fieldnames)

    # Write the header row
    writer.writeheader()

    # Process each section of data
    for i in range(0, len(sections), 2):
        algorithm = sections[i + 1].split("\n")[0]
        
        # Check if there are enough sections
        if i < len(sections):
            runs_data = sections[i]

            # Extract run data using regex
            runs = re.findall(r'Run (\d+).*?real\s+(.*?)\s+user\s+(.*?)\s+sys\s+(.*?)\s+', runs_data, re.DOTALL)

            # Write data to CSV
            for run in runs:
                run_number, real_time, user_time, sys_time = run
                search_value = re.search(r'Searching for (\d+)', runs_data)
                search_value = search_value.group(1) if search_value else ""

                writer.writerow({
                    'Algorithm': algorithm,
                    'Run': run_number,
                    'Search Value': search_value,
                    'Real Time': real_time,
                    'User Time': user_time,
                    'Sys Time': sys_time
                })
print(f"CSV file '{csv_file_path}' created successfully.")
