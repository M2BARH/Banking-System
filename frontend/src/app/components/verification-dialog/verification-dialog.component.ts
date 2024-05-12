import { AlertService } from './../../services/alert/alert.service';
import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth service/auth.service';
import { Subscription, take } from 'rxjs';
import { StringResponse } from '../../interface/login-response';

@Component({
  selector: 'app-verification-dialog',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatDialogModule,
    ReactiveFormsModule,
  ],
  templateUrl: './verification-dialog.component.html',
  styleUrl: './verification-dialog.component.css'
})
export class VerificationDialogComponent implements OnInit, OnDestroy {
  verificationForm: FormGroup;
  private subscription: Subscription | null = null;
  showProgressBar: boolean = false;

  constructor(
    public dialogRef: MatDialogRef<VerificationDialogComponent>,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private authService: AuthService,
    private alertService: AlertService
  ) {
    this.verificationForm = this.formBuilder.group({
      verificationCode: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.subscription = null;
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }

    localStorage.removeItem('verificationCode');
    localStorage.removeItem('newPassword');
  }

  onSubmit(): void {
    let verificationCode = parseInt(localStorage.getItem('verificationCode') || '', 10);

    if (isNaN(verificationCode)) {
      this.alertService.showAlert("Verification code not found or invalid");
      return;
    }
    
    if (this.verificationForm.controls['verificationCode'].value !== verificationCode) {
      this.alertService.showAlert("Code did not match");
      return;
    }
  
    if (this.verificationForm.valid) {
      this.showProgressBar = true;
  
      const resetObj = {
        email: localStorage.getItem('email'),
        password: localStorage.getItem('newPassword')
      }
  
      this.subscription = this.authService
      .resetPassword(resetObj)
      .pipe(take(1))
      .subscribe({
        next: (response: StringResponse) => {
          console.log('Response: ', response);
          if (response.successMessage) {
            this.alertService.showAlert(response.successMessage);
            this.dialogRef.close(true);
          } else if (response.errorMessage) {
            this.alertService.showAlert(response.errorMessage);
          }
        },
        error: (error) => {
          console.log('Reset failed... Error: ', error);
          this.alertService.showAlert('Error occurred while sending resetting password!!');
        },
        complete: () => {
          this.showProgressBar = false;
        }
      });
    }
  }
  

  onDismiss(): void {
    this.dialogRef.close(true);
  }
}
