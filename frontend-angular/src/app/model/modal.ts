import { faClose } from "@fortawesome/free-solid-svg-icons";

export abstract class Modal {

  readonly iconeSair = faClose;

  public abstract fecharModal(): void;

}
