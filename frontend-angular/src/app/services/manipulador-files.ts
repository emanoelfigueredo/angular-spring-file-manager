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

  public static verificarSeArquivoEnviadoEImagem(file: File): boolean {
    const tipo: string = file.type;
    const jpeg: boolean = tipo == 'image/jpeg';
    const jpg: boolean = tipo == 'image/jpg';
    const png: boolean = tipo == 'image/png';
    const gif: boolean = tipo == 'image/gif';
    const icon: boolean = tipo == 'image/x-icon';
    return jpeg || jpg || png || gif || icon;
  }

}
