declare module '@capacitor/core' {
  interface PluginRegistry {
    FFMpeg: FFMpegPlugin;
  }
}

export interface FFMpegPlugin {
  run(options: { args: string }): Promise<void>;
  write(options: { path: string, data: Uint8Array }): Promise<void>
}
