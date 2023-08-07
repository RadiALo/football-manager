import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { TeamRequest, TeamResponse } from './model/team';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private url = 'http://localhost:8080/teams';
  private  options = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private messageService: MessageService) { }
}
