export enum Tema {

  ESCURO = "escuro",
  CLARO = "claro"

}

export function trocarTema(tema: Tema): Tema {
  if(tema == Tema.CLARO) {
    tema = Tema.ESCURO;
  } else {
    tema = Tema.CLARO;
  }
  return tema;
}
