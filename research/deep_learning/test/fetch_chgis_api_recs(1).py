import csv
import requests
import json
import requests
from requests.adapters import HTTPAdapter
from urllib3.util.retry import Retry

def get_coordinates_for_place(place_name, year):
    url = f"http://maps.cga.harvard.edu/tgaz/placename?fmt=json&n={place_name}&yr={year}" # 得用http的方式请求
    
    try:

        # 如果HTTPSConnectionPool(host='maps.cga.harvard.edu', port=443)报错，请在anaconda的自己所在环境执行pip install pyopenssl。参考https://stackoverflow.com/questions/23013220/max-retries-exceeded-with-url-in-requests
        response = requests.get(url)
        data = response.json()
        
        # 检查API响应中是否有经纬度数据
        if "placenames" in data and len(data["placenames"]) > 0:
            results = []
            for place_data in data["placenames"]:
                coordinates = place_data["xy coordinates"].split(", ")
                latitude = float(coordinates[1])
                longitude = float(coordinates[0])
                parent_name = place_data.get("parent name", "")
                
                result = {
                    "place_name": place_name,
                    "year": year,
                    "latitude": latitude,
                    "longitude": longitude,
                    "parent_name": parent_name
                }
                
                results.append(result)
            
            return results
        else:
            error_msg = f"No data found for {place_name} ({year}). URL: {url}"
            return [{
                "place_name": place_name,
                "year": year,
                "parent_name": error_msg
            }]
        
    except requests.exceptions.RequestException as e:
        error_msg = f"请求API时出错：{e}"
        return [{
            "place_name": place_name,
            "year": year,
            "parent_name": error_msg
        }]

# 读取包含地名和年份的CSV文件
def read_csv_file(file_path):
    data = []
    with open(file_path, 'r', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            place_name = row['\ufeffplace_name']
            year = row['year']
            data.append((place_name, year))
    return data

# 处理每个地名，查询经纬度，并保存结果到新的CSV文件中
def process_and_save_to_csv(data, output_file_path):
    with open(output_file_path, 'w', newline='', encoding='utf-8') as csvfile:
        fieldnames = ['place_name', 'year', 'latitude', 'longitude', 'parent_name']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        
        for place_name, year in data:
            results = get_coordinates_for_place(place_name, year)
            writer.writerows(results)

if __name__ == '__main__':
    # 读取包含地名和年份的CSV文件
    input_file_path = '工作簿1.csv'
    data = read_csv_file(input_file_path)

    # 处理每个地名，查询经纬度，并保存结果到新的CSV文件中
    output_file_path = 'output.csv'
    process_and_save_to_csv(data, output_file_path)
