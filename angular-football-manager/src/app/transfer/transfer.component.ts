import { Component } from '@angular/core';

import { PlayerService} from "../player.service";
import { PlayerResponse } from '../model/player';
import { TeamService } from "../team.service";
import { TeamResponse } from '../model/team';
import { InfoResponse } from '../model/info';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.scss']
})
export class TransferComponent {
  players: PlayerResponse[] = []
  teams: TeamResponse[] = []
  transferPlayerId: number | undefined
  transferTeamId: number | undefined
  constructor(
    private playerService: PlayerService,
    private teamService: TeamService
    ) { }

  ngOnInit() {
    this.getPlayers()
    this.getTeams()
  }

  getPlayers() {
    this.playerService.getInfo().subscribe((info: InfoResponse) => {
      this.playerService.getPlayers(0, info.countOfElements).subscribe((players: PlayerResponse[]) => {
        this.players = players
      })
    })
  }

  getTeams() {
    this.teamService.getInfo().subscribe((info: InfoResponse) => {
      this.teamService.getTeams(0, info.countOfElements).subscribe((teams: TeamResponse[]) => {
        this.teams = teams
      })
    })
  }

  transfer() {
    if (this.transferTeamId === null || this.transferTeamId === undefined) {
      alert('Please select a team');
      return;
    }
    this.playerService.transferPlayer(Number(this.transferPlayerId), Number(this.transferTeamId))
      .subscribe({
        next: () => {
          alert('Player has been transferred successfully!');
        },
        error: err => {
        console.error('There was an error transferring the player', err);
      }
    });
  }
}
