import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import {RouterModule} from "@angular/router";
import {routes} from "./app.routes";

@NgModule({
  declarations: [
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    ReactiveFormsModule
  ],
  bootstrap: []
})
export class AppModule { }
