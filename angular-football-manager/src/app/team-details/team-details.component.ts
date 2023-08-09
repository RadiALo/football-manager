import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { PlayerService } from '../player.service';
import { TeamService } from '../team.service';
import { TeamResponse } from '../model/team';
import { PlayerResponse } from '../model/player';

@Component({
  selector: 'app-team-details',
  templateUrl: './team-details.component.html',
  styleUrls: ['./team-details.component.css']
})
export class TeamDetailsComponent {
  editMode = false;
  team: TeamResponse = {} as TeamResponse;
  players: PlayerResponse[] = [];
  teamId: number | undefined;

  constructor(
    private playerService: PlayerService,
    private teamService: TeamService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.teamId = +params['id'];
      if(this.teamId){
        this.getPlayersByTeamId(this.teamId);
        this.getTeamById(this.teamId);
      }
    });
  }

  getPlayersByTeamId(teamId: number): void {
    this.teamService
      .getPlayersByTeamId(teamId).subscribe(players => this.players = players);
  }

  getTeamById(teamId: number) {
    this.teamService.getTeam(teamId)
      .subscribe(team => this.team = team);
  }

  save(): void {
    this.teamService.updateTeam(this.team.id, this.team)
      .subscribe(() => this.editMode = false);
  }

  cancel(): void {
    this.editMode = false;
  }
}
