import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth service/auth.service';
import { Subscription, take } from 'rxjs';
import { RouterLink, RouterOutlet } from '@angular/router';
import { StringResponse } from '../../interface/login-response';
import { NgClass, NgIf } from '@angular/common';
import { AlertService } from '../../services/alert/alert.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    RouterOutlet,
    RouterLink,
    NgClass,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit, OnDestroy {
  loginForm: FormGroup;
  private subscription: Subscription | null = null;
  successMessage: string = '';
  errorMessage: string = '';
  color: string = '';
  passwordHidden: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private alertService: AlertService
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.subscription = null;
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  togglePasswordVisibility(fieldId: string): void {
    const field = document.getElementById(fieldId);
    if (field) {
      if (this.passwordHidden) {
        field.setAttribute('type', 'text');
      } else {
        field.setAttribute('type', 'password');
      }
      this.passwordHidden = !this.passwordHidden;
    }
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }

    const username = this.loginForm.controls['username'].value;
    const password = this.loginForm.controls['password'].value;

    this.subscription = this.authService
      .signin(username, password)
      .pipe(take(1))
      .subscribe({
        next: (response: StringResponse) => {
          console.log('Response: ', response);
          if (response.token && response.successMessage) {
            const token = response.token;
            localStorage.setItem('token', token);
          } else if (response.token === null && response.errorMessage) {
            this.alertService.showAlert(response.errorMessage);
          }
        },
        error: (error) => {
          console.error('Login failed... Error: ', error);
          this.alertService.showAlert('Login Failed!!');
        },
      });
  }
}
