import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  user: User = new User();
  confirmPassword: string = '';

  constructor(private router: Router) {}

  signup() {
    // Check if the password and confirm password match
    if (this.user.password !== this.confirmPassword) {
      alert("Passwords don't match");
      return;
    }

    // Perform signup logic here

    // If signup is successful, navigate to the dashboard
    this.router.navigate(['/dashboard']);
  }
}
