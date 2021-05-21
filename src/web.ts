import { WebPlugin, registerWebPlugin } from '@capacitor/core';
import { FFMpegPlugin } from './definitions';

export class FFMpegWeb extends WebPlugin implements FFMpegPlugin {
  constructor() {
    super({
      name: 'FFMpeg',
      platforms: ['web'],
    });
  }

  run(_: { args: string; }): Promise<{ execution_id: number; }> {
    throw new Error('Method not implemented.');
  }


}

const FFMpeg = new FFMpegWeb();

registerWebPlugin(FFMpeg);

export { FFMpeg };