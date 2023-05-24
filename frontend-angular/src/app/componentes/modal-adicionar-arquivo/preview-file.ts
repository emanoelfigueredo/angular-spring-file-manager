import { FormGroup } from '@angular/forms';
export abstract class PreviewFile {

  srcFile!: string;
  tamanhoArquivo!: string;
  uploadForm!: FormGroup;

  protected abstract obterArquivo(): void;

  protected obterUrlArquivo(file: File): void {
    this.uploadForm.patchValue({
      file: file
    });
    this.uploadForm.get('file')?.updateValueAndValidity();
    const reader = new FileReader();
    reader.onload = () => {
      this.srcFile = reader.result as string;
    }
    reader.readAsDataURL(file);
  }

}
