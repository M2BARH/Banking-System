import { NgClass, NgIf } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { VerificationDialogComponent } from '../verification-dialog/verification-dialog.component';
import { AuthService } from '../../services/auth service/auth.service';
import { Subscription, take } from 'rxjs';
import { StringResponse } from '../../interface/login-response';
import { AlertService } from '../../services/alert/alert.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgClass,
    NgIf,
    MatProgressSpinnerModule,
    RouterLink,
  ],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css',
})
export class ResetPasswordComponent implements OnInit, OnDestroy {
  resetForm: FormGroup;
  passwordHidden: boolean = true;
  private subscription: Subscription | null = null;
  verificationCode: string = '';
  showProgressBar: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private matDialog: MatDialog,
    private authService: AuthService,
    private alertService: AlertService
  ) {
    this.resetForm = this.formBuilder.group(
      {
        email: ['', [Validators.required, Validators.email]],
        newPassword: ['', Validators.required],
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

  onResetSubmit(): void {
    if (this.resetForm.invalid) {
      return;
    }

    this.showProgressBar = true;

    const resetObj = {
      email: this.resetForm.controls['email'].value.trim(),
      subject: 'Password Verification Code',
      text: 'Please use the below code to reset your password.',
    };

    localStorage.setItem(
      'email',
      this.resetForm.controls['email'].value.trim()
    );
    localStorage.setItem(
      'newPassword',
      this.resetForm.controls['newPassword'].value.trim()
    );

    this.subscription = this.authService
      .sendVerificationCode(resetObj)
      .pipe(take(1))
      .subscribe({
        next: (response: StringResponse) => {
          console.log('Response: ', response);
          if (response.successMessage) {
            localStorage.setItem('verificationCode', response.verificationCode);
            this.openVerificationModal();
          } else if (response.errorMessage) {
            this.alertService.showAlert(response.errorMessage);
          }
        },
        error: (error) => {
          console.log('Sending failed... Error: ', error);
          this.alertService.showAlert(
            'Error occurred while sending verification code!!'
          );
          this.showProgressBar = false;
        },
        complete: () => {
          this.showProgressBar = false;
          this.clearForm();
        },
      });
  }

  passwordMatchValidator(form: FormGroup) {
    let confirmPasswordError = '';

    const newPassword = form.get('newPassword')?.value.trim();
    const confirmPassword = form.get('confirmPassword')?.value.trim();

    if (newPassword && confirmPassword) {
      if (newPassword !== confirmPassword) {
        confirmPasswordError = "Passwords don't match";
      } else if (confirmPassword === '') {
        confirmPasswordError = 'Confirm password cannot be empty';
      }
    }

    return confirmPasswordError ? { confirmPasswordError } : null;
  }

  openVerificationModal(): void {
    const dialogRef = this.matDialog.open(VerificationDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('Dialog closed', result);
    });
  }

  clearForm() {
    this.resetForm.reset();
    Object.keys(this.resetForm.controls).forEach(key => {
      this.resetForm.get(key)?.setErrors(null);
    });
  }
}
