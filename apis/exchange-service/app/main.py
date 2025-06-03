from fastapi import FastAPI,Response, Header
import requests
import json


app = FastAPI()

# @app.get("/")
# async def root():
#     return {"message": "Hello World"}

@app.get("/exchange/{from_}/{to}")
def get_exchange_rate(from_: str, to: str, id_account: str = Header()):
    url = f'https://economia.awesomeapi.com.br/json/last/{from_}-{to}'
    response = requests.get(url)
    if response.status_code != 200: 
        return Response(content=json.dumps("Error fetching data"), media_type="application/json", status_code=500)
    data = response.json()
    sell = data[f"{from_}{to}"]['ask']
    buy = data[f"{from_}{to}"]['bid']
    date = data[f"{from_}{to}"]["create_date"]
    content={
        "sell": sell,   
        "buy": buy,
        "date": date,
        "id-account": id_account
    } 
    return Response(content=json.dumps(content), media_type="application/json", status_code=200)