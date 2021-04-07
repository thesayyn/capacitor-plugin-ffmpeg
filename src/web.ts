import { WebPlugin, registerWebPlugin } from '@capacitor/core';
import { FFMpegPlugin } from './definitions';
import { createFFmpeg } from "@ffmpeg/ffmpeg";

export class FFMpegWeb extends WebPlugin implements FFMpegPlugin {
  constructor() {
    super({
      name: 'FFMpeg',
      platforms: ['web'],
    });
  }

  ffmpeg = createFFmpeg({ log: true });

  private initialize() {
    if (!this.ffmpeg.isLoaded()) {
      return this.ffmpeg.load();
    }
  }

  async write(_: { path: string; data: Uint8Array; }): Promise<void> {
    await this.initialize();
    throw new Error('Method not implemented.');
  }

  async run(_: { args: string; }): Promise<void> {
    await this.initialize();
    throw new Error('Method not implemented.');
  }
}

const FFMpeg = new FFMpegWeb();

registerWebPlugin(FFMpeg);

console.log(FFMpeg);

export { FFMpeg };