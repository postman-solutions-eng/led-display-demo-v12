from final_led_display_api_sdk import FinalLedDisplayApiSdk

sdk = FinalLedDisplayApiSdk(
    api_key="YOUR_API_KEY", api_key_header="YOUR_API_KEY_HEADER", timeout=10000
)

result = sdk.predefined_icons.get_predefined_icons()

print(result)
