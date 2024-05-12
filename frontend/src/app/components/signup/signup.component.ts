import { AlertService } from './../../services/alert/alert.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../../services/auth service/auth.service';
import { Subscription, take } from 'rxjs';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { NgClass, NgIf } from '@angular/common';
import { StringResponse } from '../../interface/login-response';

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
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignUpComponent implements OnInit, OnDestroy {
  loginForm: FormGroup;
  private subscription: Subscription | null = null;
  successMessage: string = '';
  errorMessage: string = '';
  color: string = '';
  passwordHidden: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private alertService: AlertService
  ) {
    this.loginForm = this.formBuilder.group(
      {
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        identityNumber: ['', Validators.required],
        username: ['', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      },
      {
        validators: this.passwordMatchValidator,
      }
    );
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
    const passwordField = document.getElementById(fieldId);
    const confirmPasswordField = document.getElementById('confirmPassword');
    if (passwordField) {
      if (this.passwordHidden) {
        passwordField.setAttribute('type', 'text');
        confirmPasswordField?.setAttribute('type', 'text');
      } else {
        passwordField.setAttribute('type', 'password');
        confirmPasswordField?.setAttribute('type', 'password');
      }
      this.passwordHidden = !this.passwordHidden;
    }
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }

    const user = {
      firstName: this.loginForm.controls['firstName'].value.trim(),
      lastName: this.loginForm.controls['lastName'].value.trim(),
      identityNumber: this.loginForm.controls['identityNumber'].value.trim(),
      username: this.loginForm.controls['username'].value.trim(),
      password: this.loginForm.controls['password'].value.trim(),
      role: 'CUSTOMER',
    };

    this.subscription = this.authService
      .signup(user)
      .pipe(take(1))
      .subscribe({
        next: (response: StringResponse) => {
          console.log('Response: ', response);
          if (response.successMessage) {
            this.router.navigate(['/login']);
          } else if (response.errorMessage) {
            this.alertService.showAlert(response.errorMessage);
          }
        },
        error: (error) => {
          console.log('Sign up failed... Error: ', error);
          this.alertService.showAlert('Sign up Failed!!');
        },
        complete: () => {
          this.clearForm();
        }
      });
  }

  passwordMatchValidator(form: FormGroup) {
    let confirmPasswordError = '';
  
    const password = form.get('password')?.value.trim();
    const confirmPassword = form.get('confirmPassword')?.value.trim();
  
    if (password && confirmPassword) {
      if (password !== confirmPassword) {
        confirmPasswordError = "Passwords don't match";
      } else if (confirmPassword === '') {
        confirmPasswordError = 'Confirm password cannot be empty';
      }
    }
  
    return confirmPasswordError ? { confirmPasswordError } : null;
  }

  clearForm() {
    this.loginForm.reset();
    Object.keys(this.loginForm.controls).forEach(key => {
      this.loginForm.get(key)?.setErrors(null);
    });
  }
}
