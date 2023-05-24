export class FileUtil {

  public static obterNomeArquivo(file: File): string {
    let nomeArquivo: string = file.name;
    const indexUltimoPonto: number = nomeArquivo.lastIndexOf('.');
    return nomeArquivo.substring(0, indexUltimoPonto);
  }

  public static obterExtecaoArquivo(file: File): string {
    let nomeArquivo: string = file.name;
    const indexUltimoPonto: number = nomeArquivo.lastIndexOf('.');
    return nomeArquivo.substring(indexUltimoPonto, nomeArquivo.length);
  }

  public static obterTamanhoArquivo(bytes: number): string {
    const decimals = 3;
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const dm = decimals < 0 ? 0 : decimals;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
  }

}
