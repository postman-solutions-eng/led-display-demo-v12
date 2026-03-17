import { FinalLedDisplayApiSdk } from 'final-led-display-api-sdk';

(async () => {
  const finalLedDisplayApiSdk = new FinalLedDisplayApiSdk({});

  const data = await finalLedDisplayApiSdk.predefinedIcons.getPredefinedIcons();

  console.log(data);
})();
