import { Component } from '@angular/core';

import { TeamRequest, TeamResponse } from '../model/team';
import { TeamService } from '../team.service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent {
  teams: TeamResponse[] = []
  newTeam: TeamRequest = {} as TeamRequest
  page: number = 0
  size: number = 5

  constructor(private teamService: TeamService) { }

  ngOnInit() {
    this.getTeams(this.page, this.size)
  }

  getTeams(page: number, size: number) {
    this.teamService.getTeams(page, size).subscribe((teams: TeamResponse[]) => {
      this.teams = teams
    });
  }

  addTeam(): void {
    this.teamService.createTeam(this.newTeam)
      .subscribe(team => {
        this.teams.push(team)
        this.newTeam = {} as TeamRequest
      });
  }

  deleteTeam(team: TeamResponse): void {
    this.teamService.deleteTeam(team.id).subscribe(() => {
      this.teams = this.teams.filter(t => t !== team)
    });
  }

  nextPage(): void {
    this.page++
    this.getTeams(this.page, this.size)
  }

  prevPage(): void {
    this.page--
    this.getTeams(this.page, this.size)
  }
}
