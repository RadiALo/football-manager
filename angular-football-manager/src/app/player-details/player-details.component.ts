import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { PlayerService } from '../player.service';
import { TeamService } from '../team.service';
import { PlayerResponse } from '../model/player';
import { TeamResponse } from '../model/team';
import { InfoResponse } from '../model/info';

@Component({
  selector: 'app-player-details',
  templateUrl: './player-details.component.html',
  styleUrls: ['./player-details.component.scss']
})
export class PlayerDetailsComponent {
  player: PlayerResponse = {} as PlayerResponse
  teams: TeamResponse[] = []
  editMode = false

  constructor(
    private playerService: PlayerService,
    private teamService: TeamService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getTeams()
    this.getPlayer()
  }

  getPlayer(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.playerService.getPlayer(id)
      .subscribe(player => {
        this.player = player
      })
  }

  getTeams() {
    this.teamService.getInfo().subscribe((info: InfoResponse) => {
      this.teamService.getTeams(0, info.countOfElements)
          .subscribe((teams: TeamResponse[]) => {
            this.teams = teams
          })
      })
  }

  getTeamName(teamId: number): string {
    const team = this.teams.find(team => team.id === teamId)
    return team ? team.name : 'Unknown'
  }

  save(): void {
    this.playerService.updatePlayer(this.player.id, this.player).subscribe({
      next: updatedPlayer => {
        this.player = updatedPlayer
      },
      error: error => {
        console.error("There was an error updating the player", error)
        this.getPlayer()
      }
    });
    this.editMode = false;
  }

  cancel(): void {
    this.editMode = false;
    this.getPlayer();
  }
}
