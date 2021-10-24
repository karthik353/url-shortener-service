import { Component, OnInit } from '@angular/core';
import { GridApi, GridOptions, GridReadyEvent } from 'ag-grid-community';
import { Url } from '../model/url.model';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public inputUrl: string;
  public rowData: any = [];
  public urlDetails!: any;
  public shortenedUrl: string;
  public errorMessage: string;

  public gridOptions: GridOptions = {
    columnDefs: [
      { headerName: 'Key', field: 'key', width: 100 },
      {
        headerName: 'Shortened URL', cellRenderer: function (params: any) {
          return '<a href="' + window.location.origin + '/' + params.data.key + '" target="_blank">' + window.location.origin + '/' + params.data.key + '</a>';
        }, maxWidth: 275
      },
      { headerName: 'Original URL', field: 'originalUrl' },
      { headerName: 'No of Times Accessed', field: 'accessCount', maxWidth:150 }
    ],
    defaultColDef: {
      resizable: true
    },
    pagination: true,
    onGridReady: (event: GridReadyEvent) => {
      this.api = event.api;
      event.api?.sizeColumnsToFit();
    },
    overlayLoadingTemplate: 'Loading all the URL records',
    paginationPageSize: 10,
    enableCellTextSelection: true,
    suppressRowClickSelection: true
  }

  private api?: GridApi;

  constructor(private homeService: HomeService) {
    this.inputUrl = '';
    this.shortenedUrl = '';
    this.errorMessage = '';
    this.urlDetails = null;
  }

  ngOnInit(): void {
    this.getAllUrlRecords();
  }

  getAllUrlRecords() {
    setInterval(() => {
      this.homeService.getAllUrls().subscribe(response => {
        this.rowData = response;
        this.api?.setRowData(this.rowData);
      },
        error => console.log(error))
    }, 5000);
  }

  public submitChanges() {
    this.errorMessage = '';
    if (this.inputUrl.length > 0) {
      this.urlDetails = null;
      let url: Url = { key: '', originalUrl: this.inputUrl };
      this.homeService.createNewUrlRecord(url).subscribe(
        response => {
          this.urlDetails = response;
          this.inputUrl = '';
          this.setShortenedUrl(this.urlDetails.key);
        },
        error => {
          console.log(error);
          this.errorMessage = error.error;
        }
      );
    } else {
      this.errorMessage = 'Please check the input URL';
      setTimeout(() => this.errorMessage = '', 5000);
    }

  }

  setShortenedUrl(key: string) {
    this.shortenedUrl = window.location.origin + '/' + key;
  }

}

