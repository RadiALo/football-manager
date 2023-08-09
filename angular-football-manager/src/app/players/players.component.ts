import { Component } from '@angular/core';

import { PlayerService} from "../player.service";
import { PlayerRequest, PlayerResponse } from "../model/player";
import { TeamService } from "../team.service";
import { TeamResponse } from "../model/team";
import { InfoResponse } from '../model/info';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent {
  info: InfoResponse = {} as InfoResponse
  players: PlayerResponse[] = [];
  teams: TeamResponse[] = [];
  newPlayer: PlayerRequest = {} as PlayerRequest;
  page: number = 0;
  size: number = 5;
  
  constructor(
    private playerService: PlayerService,
    private teamService: TeamService
    ) { }

  ngOnInit() {
    this.getPlayers()
    this.getInfo()
  }

  nextPage() {
    this.page++
    this.getPlayers()
  }

  prevPage() {
    this.page--
    this.getPlayers()
  }

  getPlayers() {
    this.playerService.getPlayers(this.page, this.size).subscribe((players: PlayerResponse[]) => {
      this.players = players
      
    })
    this.teamService.getInfo().subscribe((info: InfoResponse) => {
      this.teamService.getTeams(0, info.countOfElements).subscribe((teams: TeamResponse[]) => {
        this.teams = teams
      })
    })
  }

  deletePlayer(player: PlayerResponse): void {
    this.playerService.deletePlayer(player.id).subscribe(() => {
      this.players = this.players.filter(t => t !== player)
    })
  }

  addPlayer(): void {
    this.playerService.createPlayer(this.newPlayer)
      .subscribe(player => {
        this.players.push(player)
        this.newPlayer = {} as PlayerRequest
    })
  }

  getTeamName(teamId: number): string {
    const team = this.teams.find(team => team.id === teamId)
    return team ? team.name : 'NaN'
  }

  getInfo() {
    this.playerService.getInfo().subscribe((info: InfoResponse) => {
      this.info = info
    });
  }

}
