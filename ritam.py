from mitmproxy import http
import json

# Define the values directly
api_url = 'https://www.lambdatest.com/resources/js/zohocrm.js'
request_to_mock = 'Server'
data_to_mock = '.py'
def response(flow: http.HTTPFlow) -> None:
    if flow.request.pretty_url == api_url :
        # Modify the specified header
        flow.response.headers[request_to_mock] = data_to_mock

        # Save headers to a file
        headers = {k: v for k, v in flow.response.headers.items()}
        with open("modified_headers.json", "w") as file:
            json.dump(headers, file, indent=2)
