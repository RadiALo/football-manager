import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { TeamRequest, TeamResponse } from './model/team';
import { PlayerResponse } from './model/player';
import { MessageService } from './message.service';
import { InfoResponse } from './model/info';

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private url = 'http://localhost:8080/teams';
  private  options = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private messageService: MessageService) { }
  
  getInfo(): Observable<InfoResponse> {
    return this.http.get<InfoResponse>(`${this.url}/info`)
      .pipe(
        tap(_ => this.messageService.add(`PlayerService: Players info fetched`))
      );
  }

  getTeams(page: number, size: number): Observable<TeamResponse[]> {
    return this.http.get<TeamResponse[]>(`${this.url}?page=${page}&size=${size}`)
      .pipe(
        tap(_ => this.messageService.add('TeamService: Teams fetched.'))
      );
  }

  getTeam(id: number): Observable<TeamResponse> {
    return this.http.get<TeamResponse>(`${this.url}/${id}`)
      .pipe(
        tap(_ => this.messageService.add(`TeamService: Team fethced by id ${id}`))
      );
  }

  createTeam(team: TeamRequest): Observable<TeamResponse> {
    return this.http.post<TeamResponse>(this.url, team, this.options)
      .pipe(
        tap((newTeam: TeamResponse) => this.messageService.add(`TeamService: Created team with id ${newTeam.id}`))
      );
  }

  updateTeam(id: number, player: TeamRequest): Observable<TeamResponse> {
    return this.http.put<TeamResponse>(`${this.url}/${id}`, player, this.options)
      .pipe(
        tap(_ => this.messageService.add(`TeamService: Updated team by id ${id}`))
      );
  }

  deleteTeam(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.options)
      .pipe(
        tap(_ => this.messageService.add(`TeamService: Deleted team by id ${id}`))
      );
  }

  getPlayersByTeamId(teamId: number): Observable<PlayerResponse[]> {
    return this.http.get<PlayerResponse[]>(`${this.url}/${teamId}/players`)
      .pipe(
        tap(_ => this.messageService.add(`TeamService: Fetched players for team with id ${teamId}`))
      );
  }

  initTeams(players: TeamRequest[]): Observable<string> {
    return this.http.post<string>(`${this.url}/init`, players, this.options)
      .pipe(
        tap(_ => this
          .messageService.add(`TeamService: Teams intitalized`))
      );
  }
}
