import requests

id = int(input("Введите id человека\n"))

URL = f"http://localhost:8080/people/{id}"

r = requests.get(url = URL)

person = r.json()

print(f"Имя человека: {person['name']}")