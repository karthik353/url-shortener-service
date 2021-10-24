import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RedirectService } from './redirect.service';
import { Url } from '../model/url.model';

@Component({
  selector: 'app-redirect',
  templateUrl: './redirect.component.html',
  styleUrls: ['./redirect.component.scss']
})
export class RedirectComponent implements OnInit {

  pathParam: any = '';
  public warning: string = '';

  constructor(@Inject(DOCUMENT) private document: Document, private activatedRoute: ActivatedRoute,
    private redirectService: RedirectService, private router: Router) {

  }

  ngOnInit(): void {
    this.pathParam = this.activatedRoute.snapshot.paramMap.get('id');
    console.log('URL key: ' + this.pathParam);
    this.redirectToExternalUrl(this.pathParam);
  }

  redirectToExternalUrl(key: string) {
    this.redirectService.getOriginalUrl(this.pathParam).subscribe(response => {
      const url: Url = response;
      if (url.originalUrl)
        this.document.location.href = url.originalUrl;
      else
        this.setWarning('Issue redirecting to the Original URL. Please check the short URL used.');
    },
      error => {
        console.log('Error =>' + JSON.stringify(error));
        this.setWarning(error.error.message);
      });
  }

  setWarning(text: string) {
    this.warning = text;
    setTimeout(() => {
      this.warning = '';
      this.router.navigateByUrl('/home');
    }, 5000);
  }

}
