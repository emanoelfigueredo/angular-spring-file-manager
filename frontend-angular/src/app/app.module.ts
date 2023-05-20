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
    SocialMediaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
