import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

import { InfoResponse } from './model/info';
import { PlayerRequest, PlayerResponse } from './model/player';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private url = 'http://localhost:8080/players';
  private options = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private messageService: MessageService) { }

  getInfo(): Observable<InfoResponse> {
    return this.http.get<InfoResponse>(`${this.url}/info`)
      .pipe(
        tap(_ => this.messageService.add(`PlayerService: Players info fetched`))
      );
  }

  getPlayers(page: number, size: number): Observable<PlayerResponse[]> {
    return this.http.get<PlayerResponse[]>(`${this.url}?page=${page}&size=${size}`)
      .pipe(
        tap(_ => this.messageService.add('PlayerService: Players fetched.'))
      );
  }

  getPlayer(id: number): Observable<PlayerResponse> {
    return this.http.get<PlayerResponse>(`${this.url}/${id}`)
      .pipe(
        tap(_ => this.messageService.add(`PlayerService: Player fethced by id ${id}`))
      );
  }

  createPlayer(player: PlayerRequest): Observable<PlayerResponse> {
    return this.http.post<PlayerResponse>(this.url, player, this.options)
      .pipe(
        tap((newPlayer: PlayerResponse) => this.messageService.add(`PlayerService: Created player with id ${newPlayer.id}`))
      );
  }

  updatePlayer(id: number, player: PlayerRequest): Observable<PlayerResponse> {
    return this.http.put<PlayerResponse>(`${this.url}/${id}`, player, this.options)
      .pipe(
        tap(_ => this.messageService.add(`PlayerService: Updated player by id ${id}`))
      );
  }

  deletePlayer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.options)
      .pipe(
        tap(_ => this.messageService.add(`PlayerService: Deleted player by id ${id}`))
      );
  }

  transferPlayer(playerId: number, teamId: number): Observable<void> {
    return this.http
      .post<void>(`${this.url}/${playerId}/transfer`, { teamId }, this.options)
      .pipe(
        tap(_ => this.messageService.add(`PlayerService: Transferred player with id ${playerId} to team with id ${teamId}`))
      );
  }

  initPlayers(players: PlayerRequest[]): Observable<string> {
    return this.http.post<string>(`${this.url}/init`, players, this.options)
      .pipe(
        tap(_ => this
          .messageService.add(`PlayerService: Players intitalized`))
      );
  }
}
