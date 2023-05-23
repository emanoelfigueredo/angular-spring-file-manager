import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './componentes/header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BarraLateralComponent } from './componentes/barra-lateral/barra-lateral.component';
import { DiretoriosCardComponent } from './componentes/view/diretorios-card/diretorios-card.component';
import { ViewComponent } from './componentes/view/view.component';
import { DiretorioComponent } from './model/diretorio/diretorio.component';
import { BotaoPageComponent } from './componentes/botao-page/botao-page.component';
import { FilesViewComponent } from './componentes/view/files-view/files-view.component';
import { PaginacaoComponent } from './componentes/view/paginacao/paginacao.component';
import { InputComponent } from './componentes/input/input.component';
import { ArquivoComponent } from './model/arquivo/arquivo.component';
import { FooterComponent } from './componentes/footer/footer.component';
import { SocialMediaComponent } from './componentes/footer/social-media/social-media.component';
import { ModalAdicionarDiretorioComponent } from './componentes/modal-adicionar-diretorio/modal-adicionar-diretorio.component';
import { EntryComponent } from './componentes/entry/entry.component';
import { ModalAdicionarArquivoComponent } from './componentes/modal-adicionar-arquivo/modal-adicionar-arquivo.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxDropzoneModule } from 'ngx-dropzone';
import { PreviewImagemComponent } from './componentes/modal-adicionar-arquivo/preview-imagem/preview-imagem.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BarraLateralComponent,
    DiretoriosCardComponent,
    ViewComponent,
    DiretorioComponent,
    BotaoPageComponent,
    FilesViewComponent,
    PaginacaoComponent,
    InputComponent,
    ArquivoComponent,
    FooterComponent,
    SocialMediaComponent,
    ModalAdicionarDiretorioComponent,
    EntryComponent,
    ModalAdicionarArquivoComponent,
    PreviewImagemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    NgxDropzoneModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
